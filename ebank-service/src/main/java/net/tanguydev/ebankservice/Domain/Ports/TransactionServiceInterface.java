
package net.tanguydev.ebankservice.Domain.Ports;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;

public interface TransactionServiceInterface {
    DomainTransaction deposit(String id, Double amount);
    DomainTransaction withdraw(String id, Double amount);
    DomainTransaction transfert(String id, String destinationId, Double amount);
}
