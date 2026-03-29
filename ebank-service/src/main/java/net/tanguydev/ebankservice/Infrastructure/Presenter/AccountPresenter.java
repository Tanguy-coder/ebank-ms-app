package net.tanguydev.ebankservice.Infrastructure.Presenter;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Presenter.AccountPresenterInterface;
import net.tanguydev.ebankservice.Domain.Response.AccountResponse;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;

import java.util.List;

public class AccountPresenter implements AccountPresenterInterface {
    private final AccountMapper mapper;

    public AccountPresenter(AccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AccountResponse present(DomainBankAccount domainBankAccount) {
        return this.mapper.toResponse(domainBankAccount);
    }

    @Override
    public List<AccountResponse> presentList(List<DomainBankAccount> domainBankAccounts) {
        return this.mapper.toResponseList(domainBankAccounts);
    }
}
