package net.tanguydev.ebankservice.Infrastructure.Mappers;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Response.DepositResponse;
import net.tanguydev.ebankservice.Domain.Response.TransferResponse;
import net.tanguydev.ebankservice.Domain.Response.WithdrawResponse;
import net.tanguydev.ebankservice.Infrastructure.Models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    DomainTransaction toDomain(Transaction transaction);
    Transaction toJpa(DomainTransaction domainTransaction);

    @Mapping(source = "tx.id", target = "transactionId")
    @Mapping(source = "tx.amount", target = "amount")
    @Mapping(source = "tx.sourceAccountId", target = "sourceAccountId")
    @Mapping(source = "from.balance", target = "fromBalance")
    @Mapping(source = "to.balance", target = "toBalance")
    @Mapping(source = "tx.destinationAccountId", target = "destinationAccountId")
    @Mapping(source = "tx.type", target = "type")
    @Mapping(source = "tx.status", target = "status")
    @Mapping(source = "tx.createdAt", target = "createdAt")
    TransferResponse toTransferResponse(DomainTransaction tx, DomainBankAccount from, DomainBankAccount to);

    @Mapping(source = "tx.id", target = "transactionId")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "tx.amount", target = "amount")
    @Mapping(source = "account.balance", target = "newBalance")
    @Mapping(source = "tx.createdAt", target = "createdAt")
    @Mapping(source = "tx.status", target = "status")
    DepositResponse toDepositResponse(DomainTransaction tx, DomainBankAccount account);

    @Mapping(source = "tx.id", target = "transactionId")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "tx.amount", target = "amount")
    @Mapping(source = "account.balance", target = "newBalance")
    @Mapping(source = "tx.createdAt", target = "createdAt")
    @Mapping(source = "tx.status", target = "status")
    WithdrawResponse toWithdrawResponse(DomainTransaction tx, DomainBankAccount account);

}
