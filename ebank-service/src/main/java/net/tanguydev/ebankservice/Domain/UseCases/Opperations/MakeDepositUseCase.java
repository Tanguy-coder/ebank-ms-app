package net.tanguydev.ebankservice.Domain.UseCases.Opperations;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;

public class MakeDepositUseCase implements MakeDepositUseCaseInterface{
    private final TransactionServiceInterface transactionService;

    public MakeDepositUseCase(TransactionServiceInterface transactionService) {
        this.transactionService = transactionService;
    }


    @Override
    public DomainTransaction execute(String id, Double amount) {
     return  this.transactionService.deposit(id, amount);
    }
}
