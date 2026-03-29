package net.tanguydev.ebankservice.Domain.UseCases;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountsUseCaseTest {
    @Mock
    private AccountServiceInterface accountService;

    @InjectMocks
    private GetAccountsUseCase accountsUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void accountsUseCase_success() {
        //Given
        List<DomainBankAccount> mockAccounts = Arrays.asList(
                new DomainBankAccount("qwerty",null,1000.0,null,null,null),
                new DomainBankAccount("afggf",null,2000.0,null,null,null)
        );

        when(accountService.getAll()).thenReturn(mockAccounts);

        //When
        List<DomainBankAccount> results = accountsUseCase.execute();

        //Then
        assert results.size() == 2;
        assert results.get(0).getBalance() == 1000.0;
        assert results.get(1).getBalance() == 2000.0;
        assert results.get(0).getId() == mockAccounts.get(0).getId();
        assert results.get(1).getId() == mockAccounts.get(1).getId();

        verify(accountService, times(1)).getAll();
    }
}
