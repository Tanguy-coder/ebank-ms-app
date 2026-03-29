package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;

public interface GetAccountByCustomerIdUseCaseInterface {
    DomainBankAccount execute(Long customerId);
}
