package net.tanguydev.ebankservice.Domain.UseCases;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class GetAccountByIdTest {
    @Mock
    private AccountServiceInterface accountService;

    @InjectMocks
    private GetAccountByIdUseCase getAccountByIdUseCase;

    @BeforeEach
    void setUp() {MockitoAnnotations.openMocks(this);}

    @Test
    void getAccountByIdUseCase_success() {
        //Given
        DomainBankAccount domainBankAccount = new DomainBankAccount("qwerty",null,1000.0, AccountType.SAVING_ACCOUNT,null,null);

        //When
        when(accountService.findById(domainBankAccount.getId())).thenReturn(Optional.of(domainBankAccount));

        //Then
        DomainBankAccount result = getAccountByIdUseCase.execute(domainBankAccount.getId());
        assert result.equals(domainBankAccount);
        assert result.getBalance() == 1000.0;
        assert result.getId().equals(domainBankAccount.getId());
        assert result.getCreatedAt() == domainBankAccount.getCreatedAt();
        assert result.getCustomer() == domainBankAccount.getCustomer();
        assert result.getType() == domainBankAccount.getType();
    }
}
