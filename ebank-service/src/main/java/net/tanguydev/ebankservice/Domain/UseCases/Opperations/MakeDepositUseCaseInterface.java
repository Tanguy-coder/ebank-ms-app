package net.tanguydev.ebankservice.Domain.UseCases.Opperations;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;

public interface MakeDepositUseCaseInterface {
    DomainTransaction execute(String id, Double amount);
}
