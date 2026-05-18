package net.tanguydev.ebankservice.Infrastructure.Controllers;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Response.AccountResponse;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountsUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.CreateAccountUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByCustomerIdUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByIdUseCaseInterface;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import net.tanguydev.ebankservice.Infrastructure.Presenter.AccountPresenter;
import net.tanguydev.ebankservice.Infrastructure.Request.AccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountPresenter presenter;
    private final CreateAccountUseCaseInterface createAccountUseCase;
    private final GetAccountsUseCaseInterface accountsUseCase;
    private final GetAccountByIdUseCaseInterface getAccountByIdUseCase;
    private final GetAccountByCustomerIdUseCaseInterface getAccountByCustomerIdUseCase;
    private final AccountMapper mapper;

    public AccountController(AccountPresenter presenter,
                             CreateAccountUseCaseInterface createAccountUseCase,
                             GetAccountsUseCaseInterface accountsUseCase,
                             GetAccountByIdUseCaseInterface getAccountByIdUseCase,
                             GetAccountByCustomerIdUseCaseInterface getAccountByCustomerIdUseCase,
                             AccountMapper mapper) {
        this.presenter = presenter;
        this.createAccountUseCase = createAccountUseCase;
        this.accountsUseCase = accountsUseCase;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.getAccountByCustomerIdUseCase = getAccountByCustomerIdUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> index() {
        return ResponseEntity.ok(presenter.presentList(accountsUseCase.execute()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable String id) {
        return ResponseEntity.ok(presenter.present(getAccountByIdUseCase.execute(id)));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> store(@RequestBody AccountRequest request){
        DomainBankAccount accountToCreate = mapper.toDomain(request);
        DomainBankAccount createdAccount = createAccountUseCase.execute(accountToCreate);
        return ResponseEntity.ok(presenter.present(createdAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable String id, @RequestBody AccountRequest request){
        DomainBankAccount accountToUpdate = mapper.toDomain(request);
        DomainBankAccount updatedAccount = getAccountByIdUseCase.execute(id);
        updatedAccount.setBalance(accountToUpdate.getBalance());
        return ResponseEntity.ok(presenter.present(updatedAccount));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<AccountResponse> getAccountByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(presenter.present(getAccountByCustomerIdUseCase.execute(customerId)));
    }

}
