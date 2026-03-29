package net.tanguydev.ebankservice.Infrastructure.Mappers;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Response.AccountResponse;
import net.tanguydev.ebankservice.Infrastructure.Models.BankAccount;
import net.tanguydev.ebankservice.Infrastructure.Request.AccountRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    DomainBankAccount toDomain(BankAccount bankAccount);
    BankAccount toJpa(DomainBankAccount domainBankAccount);
    List<DomainBankAccount> toDomainList(List<BankAccount> bankAccounts);
    List<BankAccount> toJpaList(List<DomainBankAccount> domainBankAccounts);

    AccountResponse toResponse(DomainBankAccount domainBankAccount);
    List<AccountResponse> toResponseList(List<DomainBankAccount> domainBankAccounts);

    DomainBankAccount toDomain(AccountRequest accountRequest);
}
