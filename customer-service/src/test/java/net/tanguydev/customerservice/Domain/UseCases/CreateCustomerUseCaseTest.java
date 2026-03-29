package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCustomerUseCaseTest {

    @Mock
    private CustomerServiceInterface customerService;

    @InjectMocks
    private CreateCustomerUseCase createCustomerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        // Given
        DomainCustomer inputCustomer = new DomainCustomer(null, "John Doe", "john@mail.com", "0612345678");
        DomainCustomer savedCustomer = new DomainCustomer(1L, "John Doe", "john@mail.com", "0612345678");
        
        when(customerService.save(inputCustomer)).thenReturn(savedCustomer);

        // When
        DomainCustomer result = createCustomerUseCase.execute(inputCustomer);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getFullName());
        verify(customerService, times(1)).save(inputCustomer);
    }
}
