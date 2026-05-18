package net.tanguydev.customerservice.Infrastructure.Mapper;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Response.CustomerResponse;
import net.tanguydev.customerservice.Infrastructure.Models.Customer;
import net.tanguydev.customerservice.Infrastructure.Request.CustomerRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerMapperTest {

    // Implémentation manuelle pour les tests
    private static class CustomerMapperTestImpl implements CustomerMapper {
        @Override
        public DomainCustomer toDomain(Customer customer) {
            return new DomainCustomer(customer.getId(), customer.getFullName(), customer.getEmail(), customer.getPhoneNumber());
        }

        @Override
        public Customer toJpa(DomainCustomer domainCustomer) {
            return Customer.builder()
                    .id(domainCustomer.getId())
                    .fullName(domainCustomer.getFullName())
                    .email(domainCustomer.getEmail())
                    .phoneNumber(domainCustomer.getPhoneNumber())
                    .build();
        }

        @Override
        public List<DomainCustomer> toDomainList(List<Customer> customers) {
            return customers.stream().map(this::toDomain).toList();
        }

        @Override
        public List<Customer> toJpaList(List<DomainCustomer> domainCustomers) {
            return domainCustomers.stream().map(this::toJpa).toList();
        }

        @Override
        public CustomerResponse toResponse(DomainCustomer domainCustomer) {
            return new CustomerResponse(domainCustomer.getId(), domainCustomer.getFullName(), domainCustomer.getEmail(), domainCustomer.getPhoneNumber());
        }

        @Override
        public DomainCustomer toDomain(CustomerRequest customerRequest) {
            return new DomainCustomer(null, customerRequest.getFullName(), customerRequest.getEmail(), customerRequest.getPhoneNumber());
        }
    }

    private final CustomerMapper mapper = new CustomerMapperTestImpl();

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
