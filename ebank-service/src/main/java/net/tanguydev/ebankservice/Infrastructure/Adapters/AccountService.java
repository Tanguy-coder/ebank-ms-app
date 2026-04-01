package net.tanguydev.ebankservice.Infrastructure.Adapters;

import net.tanguydev.ebankservice.Domain.Entities.Customer;
import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Gateways.AccountRepositoryInterface;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Infrastructure.Feign.CustomerRestClient;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService implements AccountServiceInterface {
    private final AccountRepositoryInterface accountRepository;
    private final CustomerRestClient customerRestClient;

    public AccountService(AccountRepositoryInterface accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @Override
    @McpTool(description = "Save an account")
    public DomainBankAccount save(@McpToolParam(description = "Account to save") DomainBankAccount domainBankAccount) {
        try {
            Customer customer = customerRestClient.getCustomerById(domainBankAccount.getCustomerId());
            domainBankAccount.setCustomer(customer);
            domainBankAccount.setId(UUID.randomUUID().toString());
            domainBankAccount.setCreatedAt(new Date());
            return this.accountRepository.save(domainBankAccount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @McpTool(description = "Find an account by id")
    public Optional<DomainBankAccount> findById(@McpToolParam(description = "The account id") String id) {
        DomainBankAccount domainBankAccount = this.accountRepository.findById(id);
        domainBankAccount.setCustomer(customerRestClient.getCustomerById(domainBankAccount.getCustomerId()));
        return Optional.of(domainBankAccount);
    }

    @Override
    @McpTool(description = "Find an account by customer id")
    public DomainBankAccount findByCustomerId(@McpToolParam(description = "The customer id") Long customerId) {
        return this.accountRepository.findByCustomerId(customerId);
    }

    @Override
    @McpTool(description = "Get all accounts")
    public List<DomainBankAccount> getAll() {
        return this.accountRepository.getAll();
    }


}
