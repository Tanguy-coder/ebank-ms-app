package net.tanguydev.customerservice.Infrastructure.Adapters;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Gateway.CustomerRepositoryInterface;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepositoryInterface customerRepository;

    public CustomerService(CustomerRepositoryInterface customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public DomainCustomer save(DomainCustomer domainCustomer) {
        return this.customerRepository.save(domainCustomer);
    }

    @Override
    public List<DomainCustomer> getAll() {
        return this.customerRepository.getAll();
    }

    @Override
    public DomainCustomer findById(Long id) {
        return this.customerRepository.findById(id);
    }
}
