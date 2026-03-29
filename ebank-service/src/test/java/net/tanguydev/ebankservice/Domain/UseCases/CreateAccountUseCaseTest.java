package net.tanguydev.ebankservice.Domain.UseCases;

import net.tanguydev.ebankservice.Domain.Entities.Customer;
import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.CreateAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateAccountUseCaseTest {
    @Mock
    private AccountServiceInterface accountService;

    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAccountSuccessfully() {
        //Given
        Long customerId = 1L;
        Customer customer = new Customer();
        DomainBankAccount domainBankAccount = new DomainBankAccount("qwryqrsyqfs",new Date(),1000.0, AccountType.SAVING_ACCOUNT,customerId,customer);
        DomainBankAccount savedAccount = new DomainBankAccount("qwryqrsyqfs",new Date(),1000.0, AccountType.SAVING_ACCOUNT,customerId,customer);

        when(accountService.save(domainBankAccount)).thenReturn(savedAccount);

        //When
        DomainBankAccount result = createAccountUseCase.execute(domainBankAccount);

        //Then
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getBalance());
        assertNotNull(result.getCustomerId());
        assert result.equals(savedAccount);

        verify(accountService, times(1)).save(domainBankAccount);

    }

}
