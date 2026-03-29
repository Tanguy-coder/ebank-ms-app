package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;

import java.util.List;

public class GetAccountsUseCase implements GetAccountsUseCaseInterface {
    private final AccountServiceInterface accountService;

    public GetAccountsUseCase(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    @Override
    public List<DomainBankAccount> execute() {
        return this.accountService.getAll();
    }
}
