package net.tanguydev.ebankservice.Domain.UseCases.Transactions;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;

public class MakeWithdrawUseCase implements MakeWithdrawUseCaseInterface{
    private final TransactionServiceInterface transactionService;

    public MakeWithdrawUseCase(TransactionServiceInterface transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public DomainTransaction execute(String id, Double amount) {
        return this.transactionService.withdraw(id, amount);
    }
}
