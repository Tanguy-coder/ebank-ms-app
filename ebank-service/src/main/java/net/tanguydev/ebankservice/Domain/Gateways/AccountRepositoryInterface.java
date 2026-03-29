package net.tanguydev.ebankservice.Domain.Gateways;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;

import java.util.List;

public interface AccountRepositoryInterface {
    DomainBankAccount save(DomainBankAccount domainBankAccount);
    DomainBankAccount findById(String id);
    DomainBankAccount findByCustomerId(Long customerId);
    List<DomainBankAccount> getAll();
}
