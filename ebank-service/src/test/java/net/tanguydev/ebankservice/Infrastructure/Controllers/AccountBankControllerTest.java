package net.tanguydev.ebankservice.Infrastructure.Controllers;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import net.tanguydev.ebankservice.Domain.Presenter.AccountPresenterInterface;
import net.tanguydev.ebankservice.Domain.Response.AccountResponse;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountsUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.CreateAccountUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByIdUseCaseInterface;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AccountController.class)
public class AccountBankControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CreateAccountUseCaseInterface createAccountUseCase;
    @MockitoBean
    private GetAccountsUseCaseInterface accountsUseCase;
    @MockitoBean
    private GetAccountByIdUseCaseInterface getAccountByIdUseCase;
    @MockitoBean
    private AccountPresenterInterface presenter;
    @MockitoBean
    private AccountMapper mapper;

    @Test
    void should_return_all_accounts() throws Exception {
        //Given
        List<DomainBankAccount> domainBankAccounts = Arrays.asList(
                new DomainBankAccount("qwerty",null,1000.0, AccountType.CURRENT_ACCOUNT,2L,null)
        );

        List<AccountResponse> responses = Arrays.asList(
                new AccountResponse("qwerty", new Date(),1000.0,AccountType.CURRENT_ACCOUNT,"2L",null)
        );

        when(accountsUseCase.execute()).thenReturn(domainBankAccounts);
        when(presenter.presentList(domainBankAccounts)).thenReturn(responses);

        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value("qwerty"))
                .andExpect(jsonPath("$[0].balance").value(1000.0));

    }

}
