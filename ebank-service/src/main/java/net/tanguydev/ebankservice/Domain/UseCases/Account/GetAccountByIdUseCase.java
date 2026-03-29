package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.Validation.Exception.AccountNotFoundException;

public class GetAccountByIdUseCase implements GetAccountByIdUseCaseInterface{
    private final AccountServiceInterface accountService;

    public GetAccountByIdUseCase(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    @Override
    public DomainBankAccount execute(String id) {
        return this.accountService.findById(id).orElseThrow(AccountNotFoundException::new);
    }
}
