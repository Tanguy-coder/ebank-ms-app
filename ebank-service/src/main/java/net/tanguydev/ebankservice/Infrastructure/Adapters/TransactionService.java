
package net.tanguydev.ebankservice.Infrastructure.Adapters;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;
import net.tanguydev.ebankservice.Domain.Entities.TransactionType;
import net.tanguydev.ebankservice.Domain.Gateways.AccountRepositoryInterface;
import net.tanguydev.ebankservice.Domain.Gateways.TransactionRepositoryInterface;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;
import net.tanguydev.ebankservice.Domain.Validation.Exception.InsufficientBalanceException;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionService implements TransactionServiceInterface {
    private final TransactionRepositoryInterface repository;
    private final AccountRepositoryInterface accountRepository ;

    public TransactionService(TransactionRepositoryInterface repository, AccountRepositoryInterface accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    @Override
    public DomainTransaction deposit(String id, Double amount) {
        return this.repository.deposit(id, amount);
    }

    @Override
    public DomainTransaction withdraw(String id, Double amount) {
        DomainBankAccount account = accountRepository.findById(id);
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
        DomainBankAccount fromAccount = accountRepository.findById(id);
        DomainBankAccount toAccount = accountRepository.findById(destinationId);
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        return null;
    }
}
