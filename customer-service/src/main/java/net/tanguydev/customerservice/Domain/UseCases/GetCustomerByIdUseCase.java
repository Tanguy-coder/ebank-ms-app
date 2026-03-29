package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;

public class GetCustomerByIdUseCase implements GetCustomerByIdUseCaseInterface {
    private final CustomerServiceInterface customerService;

    public GetCustomerByIdUseCase(CustomerServiceInterface customerService) {
        this.customerService = customerService;
    }

    @Override
    public DomainCustomer execute(Long id) {
        return this.customerService.findById(id);
    }
}
