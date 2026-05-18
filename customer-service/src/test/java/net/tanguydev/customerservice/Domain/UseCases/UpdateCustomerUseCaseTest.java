package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCustomerUseCaseTest {

    // @Mock crée un "faux" objet (un bouchon) pour simuler le comportement du service
    // Cela nous permet de tester le Use Case isolément de la base de données
    @Mock
    private CustomerServiceInterface customerService;

    // @InjectMocks crée l'instance réelle de UpdateCustomerUseCase et y injecte les mocks
    @InjectMocks
    private UpdateCustomerUseCase updateCustomerUseCase;

    public UpdateCustomerUseCaseTest() {
        // Initialise les annotations Mockito dans le constructeur
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateExistingCustomerSuccessfully() {
        // --- PHASE 1 : GIVEN (Étant donné que...) ---
        // On prépare les données de test
        DomainCustomer existing = new DomainCustomer(1L, "John Doe", "john@mail.com", "0612345678");
        DomainCustomer toUpdate = new DomainCustomer(1L, "John Smith", "john.smith@mail.com", "0700000000");

        // Simuler la vérification d'existence
        when(customerService.findById(1L)).thenReturn(existing);
        // On définit le comportement attendu du mock :
        // "Quand on appelle customerService.save avec toUpdate, alors retourne toUpdate"
        when(customerService.save(toUpdate)).thenReturn(toUpdate);

        // --- PHASE 2 : WHEN (Quand...) ---
        // On exécute l'action que l'on veut tester
        DomainCustomer result = updateCustomerUseCase.execute(1L, toUpdate);

        // --- PHASE 3 : THEN (Alors...) ---
        // On vérifie que le résultat est correct (les assertions)
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Smith", result.getFullName());
        assertEquals("john.smith@mail.com", result.getEmail());
        assertEquals("0700000000", result.getPhoneNumber());

        // On vérifie que la méthode findById a été appelée
        verify(customerService, times(1)).findById(1L);
        // On vérifie que la méthode save() a bien été appelée exactement 1 fois sur le service
        verify(customerService, times(1)).save(toUpdate);
    }

    /**
     * Returns null when customer not found; verifies findById call
     */
    @Test
    void shouldReturnNullWhenCustomerDoesNotExist() {
        DomainCustomer customer = new DomainCustomer(1L,"ALI kali","kali@mail.com","980767854");

        when(customerService.findById(1L)).thenReturn(null);

        DomainCustomer result = updateCustomerUseCase.execute(1L, customer);

        assertNull(result);
        verify(customerService, times(1)).findById(1L);
    }

    /**
     * Verifies email is unchanged when null in input
     */
    @Test
    void emailShouldNotBeNull() {
        DomainCustomer existing = new DomainCustomer(1L,"ALI kali","kali@mail.com","980767854");
        DomainCustomer toUpdate = new DomainCustomer(1L,"ALI kali",null,"980767854");

        when(customerService.findById(1L)).thenReturn(existing);
        when(customerService.save(any(DomainCustomer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        DomainCustomer result = updateCustomerUseCase.execute(1L, toUpdate);
        
        assertNotNull(result);
        assertNotNull(result.getEmail());
        assertEquals("kali@mail.com", result.getEmail());

        verify(customerService, times(1)).findById(1L);
    }
}
