package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GetCustomerByIdUseCaseTest {
     @Mock
     private CustomerServiceInterface customerService;

     @InjectMocks
     private GetCustomerByIdUseCase getCustomerByIdUseCase;

     @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
     }

     @Test
     void shouldReturnCustomerWhenIdExists() {
         // Given
         Long customerId = 1L;
         DomainCustomer expectedCustomer = new DomainCustomer(customerId, "Jane Doe", "jane@mail.com", "0611");
         when(customerService.findById(customerId)).thenReturn(expectedCustomer);

         // When
         DomainCustomer result = getCustomerByIdUseCase.execute(customerId);

         // Then
         assertNotNull(result);
         assertEquals(customerId, result.getId());
         assertEquals("Jane Doe", result.getFullName());
         verify(customerService, times(1)).findById(customerId);
     }
}
