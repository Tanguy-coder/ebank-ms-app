package net.tanguydev.ebankservice.Infrastructure.Repositories;

import jakarta.transaction.Transactional;
import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Gateways.AccountRepositoryInterface;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import net.tanguydev.ebankservice.Infrastructure.Models.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements AccountRepositoryInterface {
    private final AccountJpaRepositoryInterface repository;
    private final AccountMapper mapper;

    public AccountRepository(AccountJpaRepositoryInterface repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DomainBankAccount save(DomainBankAccount domainBankAccount) {
        BankAccount jpaAccount = this.repository.save(mapper.toJpa(domainBankAccount));
        return mapper.toDomain(jpaAccount);
    }

    @Override
    public DomainBankAccount findById(String id) {
        return mapper.toDomain(this.repository.findById(id).orElseThrow(()-> new RuntimeException("Account not found")));
    }

    @Override
    public DomainBankAccount findByCustomerId(Long customerId) {
        return mapper.toDomain(this.repository.findByCustomerId(customerId));
    }

    @Override
    public List<DomainBankAccount> getAll() {
        List<BankAccount> accounts = this.repository.findAll();
        return mapper.toDomainList(accounts);
    }
}
