package net.tanguydev.customerservice.Infrastructure.Repositories;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapperImpl;
import net.tanguydev.customerservice.Infrastructure.Models.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Import(CustomerMapperImpl.class)
@EntityScan(basePackages = "net.tanguydev.customerservice.Infrastructure")
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    @BeforeEach
    public void setUp(){
        Customer customer1 = Customer.builder().id(1L).fullName("Ali Monser").email("ali@gmail.com").phoneNumber("63676327722").build();
        Customer customer2 = Customer.builder().id(1L).fullName("yaya").email("yaya@yayagroup.com").phoneNumber("sggsggsg").build();
        customerRepository.save(customerMapper.toDomain(customer1));
        customerRepository.save(customerMapper.toDomain(customer2));
    }

    @Test
    public void shouldFindCustomerByEmail(){
        String givenEmail = "ali@aligroup.com";
        DomainCustomer result = customerRepository.findByEmail(givenEmail);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result.getEmail()).isEqualTo(givenEmail);
    }

}