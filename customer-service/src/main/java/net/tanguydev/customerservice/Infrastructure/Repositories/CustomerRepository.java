package net.tanguydev.customerservice.Infrastructure.Repositories;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Gateway.CustomerRepositoryInterface;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;
import net.tanguydev.customerservice.Infrastructure.Models.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository implements CustomerRepositoryInterface {
    private final CustomerJpaRepository repository;
    private final CustomerMapper mapper;

    public CustomerRepository(CustomerJpaRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DomainCustomer save(DomainCustomer domainCustomer) {
        Customer jpaCustomer = this.repository.save(mapper.toJpa(domainCustomer));
        return mapper.toDomain(jpaCustomer);
    }

    @Override
    public List<DomainCustomer> getAll() {
        List<Customer> customers = this.repository.findAll();
        return mapper.toDomainList(customers);
    }

    @Override
    public DomainCustomer findById(Long id) {
        return mapper.toDomain(this.repository.findById(id).get());
    }

    @Override
    public DomainCustomer findByEmail(String email) {
        return mapper.toDomain(this.repository.findByEmailContainingIgnoreCase(email));
    }

}
