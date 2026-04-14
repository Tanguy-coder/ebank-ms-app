package net.tanguydev.ebankservice.Domain.UseCases;

import net.tanguydev.ebankservice.Domain.Entities.Customer;
import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByCustomerIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.*;


class GetAccountByCustomerIdUseCaseTest {
    @Mock
    private AccountServiceInterface accountService;

    @InjectMocks
    private GetAccountByCustomerIdUseCase getAccountByCustomerIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAccountByCustomerIdUseCase_success() {
        //Given
        Customer customer = new Customer(2L,"Tanguy", "mail@mail.com","63767676362");
        DomainBankAccount domainBankAccount = new DomainBankAccount("qwerty",new Date(),1000.0, AccountType.CURRENT_ACCOUNT, 2L, customer);

        when(accountService.findByCustomerId(customer.getId())).thenReturn(domainBankAccount);

        //When
        DomainBankAccount result = getAccountByCustomerIdUseCase.execute(customer.getId());

        System.out.println("Print du test " + result);
        //Then
        assert result.equals(domainBankAccount);
        assert result.getCustomerId().equals(customer.getId());
        assert result.getCustomer().equals(customer);

        verify(accountService, times(1)).findByCustomerId(customer.getId());
    }
}
