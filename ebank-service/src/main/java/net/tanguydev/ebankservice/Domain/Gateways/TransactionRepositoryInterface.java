package net.tanguydev.ebankservice.Domain.Gateways;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;

public interface TransactionRepositoryInterface {
    DomainTransaction deposit(DomainTransaction domainTransaction);
    DomainTransaction withdraw(DomainTransaction domainTransaction);
    DomainTransaction transfert(DomainTransaction domainTransaction);
}
