package net.tanguydev.ebankservice.Domain.Presenter;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Response.AccountResponse;

import java.util.List;

public interface AccountPresenterInterface {
    AccountResponse present(DomainBankAccount domainBankAccount);
    List<AccountResponse> presentList(List<DomainBankAccount> domainBankAccounts);
}
