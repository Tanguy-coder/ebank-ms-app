package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;

import java.util.List;

public interface GetAccountsUseCaseInterface {
    List<DomainBankAccount> execute() ;
}
