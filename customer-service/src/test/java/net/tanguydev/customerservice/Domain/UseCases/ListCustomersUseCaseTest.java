package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ListCustomersUseCaseTest {
    @Mock
    private CustomerServiceInterface customerService;

    @InjectMocks
    private ListCustomersUseCase listCustomersUseCase;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void listCustomers_success() {
        // Given
        List<DomainCustomer> mockCustomers = Arrays.asList(
            new DomainCustomer(1L, "Alice", "alice@mail.com", "0601"),
            new DomainCustomer(2L, "Bob", "bob@mail.com", "0602")
        );
        when(customerService.getAll()).thenReturn(mockCustomers);

        // When
        List<DomainCustomer> result = listCustomersUseCase.execute();

        // Then
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getFullName());
        assertEquals("Bob", result.get(1).getFullName());
    }
}
