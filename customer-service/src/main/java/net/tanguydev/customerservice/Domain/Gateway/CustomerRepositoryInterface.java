package net.tanguydev.customerservice.Domain.Gateway;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;

import java.util.List;

public interface CustomerRepositoryInterface {
    DomainCustomer save(DomainCustomer domainCustomer);
    List<DomainCustomer> getAll();
    DomainCustomer findById(Long id);
    DomainCustomer findByEmail(String email);
}
