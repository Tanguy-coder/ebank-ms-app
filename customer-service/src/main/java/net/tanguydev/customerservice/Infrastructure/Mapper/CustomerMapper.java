package net.tanguydev.customerservice.Infrastructure.Mapper;


import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Response.CustomerResponse;
import net.tanguydev.customerservice.Infrastructure.Models.Customer;
import net.tanguydev.customerservice.Infrastructure.Request.CustomerRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    DomainCustomer toDomain(Customer customer);
    Customer toJpa(DomainCustomer domainCustomer);
    List<DomainCustomer> toDomainList(List<Customer> customers);
    List<Customer> toJpaList(List<DomainCustomer> domainCustomers);

    CustomerResponse toResponse(DomainCustomer domainCustomer);
    DomainCustomer toDomain(CustomerRequest customerRequest);
}
