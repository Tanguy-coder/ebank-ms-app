package net.tanguydev.ebankservice.Domain.UseCases.Account;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.Validation.DomainBankAccountValidator;

public class CreateAccountUseCase implements CreateAccountUseCaseInterface{
    private final AccountServiceInterface accountService;
    private final DomainBankAccountValidator validator = new DomainBankAccountValidator();

    public CreateAccountUseCase(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    @Override
    public DomainBankAccount execute(DomainBankAccount domainBankAccount) {
        validator.validate(domainBankAccount);
        return this.accountService.save(domainBankAccount);
    }
}
