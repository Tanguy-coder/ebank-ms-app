package net.tanguydev.ebankservice.Infrastructure.Mappers;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Infrastructure.Models.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    DomainTransaction toDomain(Transaction transaction);
    Transaction toJpa(DomainTransaction domainTransaction);
    Transaction toResponse(DomainTransaction domainTransaction);

    List<Transaction> toResponseList(List<DomainTransaction> domainTransactions);
    List<DomainTransaction> toDomainList(List<Transaction> transactions);
    List<DomainTransaction> toJpaList(List<Transaction> transactions);
}
