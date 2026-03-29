package net.tanguydev.ebankservice.Domain.Ports;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;

import java.util.List;
import java.util.Optional;

public interface AccountServiceInterface {
    DomainBankAccount save(DomainBankAccount domainBankAccount);
    Optional<DomainBankAccount> findById(String id);
    DomainBankAccount findByCustomerId(Long customerId);
    List<DomainBankAccount> getAll();
}
