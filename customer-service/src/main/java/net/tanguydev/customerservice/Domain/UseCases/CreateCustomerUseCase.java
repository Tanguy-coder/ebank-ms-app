package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import net.tanguydev.customerservice.Domain.Validation.DomainCustomerValidator;

public class CreateCustomerUseCase implements CreateCustomerUseCaseInterface {

    private  final CustomerServiceInterface customerService;
    private final DomainCustomerValidator validator = new DomainCustomerValidator();

    public CreateCustomerUseCase(CustomerServiceInterface customerService) {
        this.customerService = customerService;
    }

    @Override
    public DomainCustomer execute(DomainCustomer domainCustomer) {
        validator.validate(domainCustomer);
        return this.customerService.save(domainCustomer);
    }
}
