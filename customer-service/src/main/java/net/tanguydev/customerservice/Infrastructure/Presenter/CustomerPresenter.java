package net.tanguydev.customerservice.Infrastructure.Presenter;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Presenter.CustomerPresenterInterface;
import net.tanguydev.customerservice.Domain.Response.CustomerResponse;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;

import java.util.List;

public class CustomerPresenter implements CustomerPresenterInterface {
    private final CustomerMapper mapper;

    public CustomerPresenter(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CustomerResponse present(DomainCustomer domainCustomer) {
        return this.mapper.toResponse(domainCustomer);
    }

    @Override
    public List<CustomerResponse> presentList(List<DomainCustomer> domainCustomers) {
        return domainCustomers.stream()
                .map(this.mapper::toResponse)
                .toList();
    }
}
