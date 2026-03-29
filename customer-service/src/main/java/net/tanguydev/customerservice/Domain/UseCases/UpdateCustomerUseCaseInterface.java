package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;

public interface UpdateCustomerUseCaseInterface {
    DomainCustomer execute(DomainCustomer domainCustomer);
}
