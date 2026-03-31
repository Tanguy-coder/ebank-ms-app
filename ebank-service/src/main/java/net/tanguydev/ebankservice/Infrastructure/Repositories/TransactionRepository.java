package net.tanguydev.ebankservice.Infrastructure.Repositories;

import jakarta.transaction.Transactional;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Gateways.TransactionRepositoryInterface;
import net.tanguydev.ebankservice.Infrastructure.Mappers.TransactionMapper;
import net.tanguydev.ebankservice.Infrastructure.Models.Transaction;

public class TransactionRepository implements TransactionRepositoryInterface {
    private final TransactionJpaRepository transactionJpaRepository;
    private final AccountJpaRepositoryInterface repository;
    private final TransactionMapper mapper;

    public TransactionRepository(TransactionJpaRepository transactionJpaRepository, AccountJpaRepositoryInterface repository, TransactionMapper mapper) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public DomainTransaction deposit(DomainTransaction domainTransaction) {
        Transaction jpaTransaction = this.transactionJpaRepository.save(mapper.toJpa(domainTransaction));
        return mapper.toDomain(jpaTransaction);
    }


    @Transactional
    @Override
    public DomainTransaction withdraw(DomainTransaction domainTransaction) {
        Transaction jpaTransaction = this.transactionJpaRepository.save(mapper.toJpa(domainTransaction));
        return mapper.toDomain(jpaTransaction);
    }

    @Transactional
    @Override
    public DomainTransaction transfert(DomainTransaction domainTransaction) {
        Transaction jpaTransaction = this.transactionJpaRepository.save(mapper.toJpa(domainTransaction));
        return mapper.toDomain(jpaTransaction);
    }
}
