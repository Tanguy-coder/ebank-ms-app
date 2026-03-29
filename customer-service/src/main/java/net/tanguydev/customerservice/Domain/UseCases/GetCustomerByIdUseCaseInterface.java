package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;

public interface GetCustomerByIdUseCaseInterface {
    DomainCustomer execute(Long id);
}
