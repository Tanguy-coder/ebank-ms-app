package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import net.tanguydev.customerservice.Domain.Validation.DomainCustomerValidator;

public class UpdateCustomerUseCase implements UpdateCustomerUseCaseInterface {

    private final CustomerServiceInterface customerService;
    private final DomainCustomerValidator validator = new DomainCustomerValidator();

    public UpdateCustomerUseCase(CustomerServiceInterface customerService) {
        this.customerService = customerService;
    }

    @Override
    public DomainCustomer execute(DomainCustomer domainCustomer) {
        DomainCustomer existingCustomer = this.customerService.findById(domainCustomer.getId());
        if (existingCustomer == null) {
            return null;
        }

        if (domainCustomer.getEmail() == null) {
            domainCustomer.setEmail(existingCustomer.getEmail());
        }
        validator.validate(domainCustomer);

        return this.customerService.save(domainCustomer);
    }
}
