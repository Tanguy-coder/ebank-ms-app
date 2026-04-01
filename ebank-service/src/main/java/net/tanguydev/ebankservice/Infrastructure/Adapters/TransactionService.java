
package net.tanguydev.ebankservice.Infrastructure.Adapters;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;
import net.tanguydev.ebankservice.Domain.Entities.TransactionType;
import net.tanguydev.ebankservice.Domain.Gateways.AccountRepositoryInterface;
import net.tanguydev.ebankservice.Domain.Gateways.TransactionRepositoryInterface;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;
import net.tanguydev.ebankservice.Domain.Validation.Exception.AccountNotFoundException;
import net.tanguydev.ebankservice.Domain.Validation.Exception.AvoidTransfertForTheSameAccount;
import net.tanguydev.ebankservice.Domain.Validation.Exception.InsufficientBalanceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class TransactionService implements TransactionServiceInterface {
    private final TransactionRepositoryInterface repository;
    private final AccountRepositoryInterface accountRepository ;

    public TransactionService(TransactionRepositoryInterface repository, AccountRepositoryInterface accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    @Override
    public DomainTransaction deposit(String id, Double amount) {
        DomainBankAccount account = findAccountById(id);
        account.deposit(amount);
        accountRepository.save(account);
        DomainTransaction domainTransaction = new DomainTransaction();
        domainTransaction.setId(UUID.randomUUID().toString());
        domainTransaction.setType(TransactionType.DEPOSIT);
        domainTransaction.setAmount(amount);
        domainTransaction.setSourceAccountId(null);
        domainTransaction.setDestinationAccountId(id);
        domainTransaction.setStatus(TransactionStatus.SUCCESS);
        domainTransaction.setCreatedAt(LocalDateTime.now());

        return this.repository.deposit(domainTransaction);
    }

    @Override
    public DomainTransaction withdraw(String id, Double amount) {
        DomainBankAccount account = findAccountById(id);
        account.withdraw(amount);
        accountRepository.save(account);

        DomainTransaction domainTransaction = new DomainTransaction();
        domainTransaction.setId(UUID.randomUUID().toString());
        domainTransaction.setType(TransactionType.WITHDRAWAL);
        domainTransaction.setAmount(amount);
        domainTransaction.setSourceAccountId(id);
        domainTransaction.setDestinationAccountId(null);
        domainTransaction.setStatus(TransactionStatus.SUCCESS);
        domainTransaction.setCreatedAt(LocalDateTime.now());
        return this.repository.withdraw(domainTransaction);
    }

    @Override
    public DomainTransaction transfert(String id, String destinationId, Double amount) {
        DomainBankAccount fromAccount = findAccountById(id);
        DomainBankAccount toAccount = findAccountById(destinationId);
        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new AvoidTransfertForTheSameAccount();
        }
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException();
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        DomainTransaction domainTransaction = new DomainTransaction();
        domainTransaction.setId(UUID.randomUUID().toString());
        domainTransaction.setType(TransactionType.TRANSFER);
        domainTransaction.setAmount(amount);
        domainTransaction.setSourceAccountId(id);
        domainTransaction.setDestinationAccountId(destinationId);
        domainTransaction.setStatus(TransactionStatus.SUCCESS);
        domainTransaction.setCreatedAt(LocalDateTime.now());

        return this.repository.transfert(domainTransaction);
    }

    private DomainBankAccount findAccountById(String id) {
        DomainBankAccount account = this.accountRepository.findById(id);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }
}
