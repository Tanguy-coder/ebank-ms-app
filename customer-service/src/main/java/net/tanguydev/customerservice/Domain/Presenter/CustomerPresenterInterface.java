package net.tanguydev.customerservice.Domain.Presenter;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Response.CustomerResponse;

import java.util.List;

public interface CustomerPresenterInterface {
    CustomerResponse present(DomainCustomer domainCustomer);
    List<CustomerResponse> presentList(List<DomainCustomer> domainCustomers);
}
