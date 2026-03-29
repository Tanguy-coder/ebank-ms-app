package net.tanguydev.customerservice.Domain.UseCases;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;

import java.util.List;

public class ListCustomersUseCase implements ListCustomersUseCaseInterface {
    private final CustomerServiceInterface customerServiceInterface;

    public ListCustomersUseCase(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    @Override
    public List<DomainCustomer> execute() {
        return this.customerServiceInterface.getAll();
    }
}
