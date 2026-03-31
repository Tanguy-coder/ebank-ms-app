package net.tanguydev.ebankservice.Domain.UseCases.Transactions;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;

public class TransfertUseCase implements TransfertUseCaseInterface{
    private final TransactionServiceInterface transactionService;

    public TransfertUseCase(TransactionServiceInterface transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public DomainTransaction execute(String id, String destinationId, Double amount) {
        return this.transactionService.transfert(id, destinationId, amount);
    }
}
