package net.tanguydev.ebankservice.Domain.UseCases.Opperations;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;

public interface TransfertUseCaseInterface {
    DomainTransaction execute(String id, String destinationId, Double amount);
}
