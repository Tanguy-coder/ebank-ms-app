package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;

public class GetAccountByCustomerIdUseCase implements GetAccountByCustomerIdUseCaseInterface{
    private final AccountServiceInterface accountService;

    public GetAccountByCustomerIdUseCase(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    @Override
    public DomainBankAccount execute(Long customerId) {
        if (customerId == null) {
            return null;
        }
        return this.accountService.findByCustomerId(customerId);
    }
}
