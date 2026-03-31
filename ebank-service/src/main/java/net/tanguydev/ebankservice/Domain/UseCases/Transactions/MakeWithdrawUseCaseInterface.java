package net.tanguydev.ebankservice.Domain.UseCases.Transactions;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;

public interface MakeWithdrawUseCaseInterface {
    DomainTransaction execute(String id, Double amount);
}
