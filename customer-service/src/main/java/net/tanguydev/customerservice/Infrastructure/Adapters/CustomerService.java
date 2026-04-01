package net.tanguydev.customerservice.Infrastructure.Adapters;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Gateway.CustomerRepositoryInterface;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements CustomerServiceInterface {

    private final CustomerRepositoryInterface customerRepository;

    public CustomerService(CustomerRepositoryInterface customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @McpTool(description = "Save a customer")
    public DomainCustomer save(@McpToolParam(description = "The customer to save") DomainCustomer domainCustomer) {
        return this.customerRepository.save(domainCustomer);
    }

    @Override
    @McpTool(description = "Get all customers")
    public List<DomainCustomer> getAll() {
        return this.customerRepository.getAll();
    }

    @Override
    @McpTool(description = "Get a customer by ID")
    public DomainCustomer findById(@McpToolParam(description = "The customer Id") Long id) {
        return this.customerRepository.findById(id);
    }
}
