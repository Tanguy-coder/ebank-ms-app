package net.tanguydev.customerservice.Infrastructure.Mapper;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Infrastructure.Models.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerMapperTest {

    // En test unitaire, on instancie directement l'implémentation générée par MapStruct
    private final CustomerMapper mapper = new CustomerMapperImpl();

    @Test
    void shouldMapCustomerToDomain() {
        // Given
        Customer jpaCustomer = Customer.builder()
                .id(1L)
                .fullName("Alice")
                .email("alice@mail.com")
                .phoneNumber("0601")
                .build();

        // When
        DomainCustomer domainCustomer = mapper.toDomain(jpaCustomer);

        // Then
        assertNotNull(domainCustomer);
        assertEquals(jpaCustomer.getId(), domainCustomer.getId());
        assertEquals(jpaCustomer.getFullName(), domainCustomer.getFullName());
        assertEquals(jpaCustomer.getEmail(), domainCustomer.getEmail());
        assertEquals(jpaCustomer.getPhoneNumber(), domainCustomer.getPhoneNumber());
    }

    @Test
    void shouldMapDomainToJpa() {
        // Given
        DomainCustomer domainCustomer = new DomainCustomer(1L, "Bob", "bob@mail.com", "0602");

        // When
        Customer jpaCustomer = mapper.toJpa(domainCustomer);

        // Then
        assertNotNull(jpaCustomer);
        assertEquals(domainCustomer.getId(), jpaCustomer.getId());
        assertEquals(domainCustomer.getFullName(), jpaCustomer.getFullName());
        assertEquals(domainCustomer.getEmail(), jpaCustomer.getEmail());
        assertEquals(domainCustomer.getPhoneNumber(), jpaCustomer.getPhoneNumber());
    }
}
