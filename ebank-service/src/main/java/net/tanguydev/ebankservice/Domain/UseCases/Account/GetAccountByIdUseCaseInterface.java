package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;

public interface GetAccountByIdUseCaseInterface {
    DomainBankAccount execute(String id);
}
