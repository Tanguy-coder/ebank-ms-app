package net.tanguydev.customerservice.Domain.Port;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;

import java.util.List;

public interface CustomerServiceInterface {
    DomainCustomer save(DomainCustomer domainCustomer);
    List<DomainCustomer> getAll();
    DomainCustomer findById(Long id);
}
