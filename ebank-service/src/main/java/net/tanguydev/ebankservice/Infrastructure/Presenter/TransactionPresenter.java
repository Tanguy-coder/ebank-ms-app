package net.tanguydev.ebankservice.Infrastructure.Presenter;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Presenter.TransactionPresenterInterface;
import net.tanguydev.ebankservice.Domain.Response.DepositResponse;
import net.tanguydev.ebankservice.Domain.Response.TransferResponse;
import net.tanguydev.ebankservice.Domain.Response.WithdrawResponse;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import net.tanguydev.ebankservice.Infrastructure.Mappers.TransactionMapper;

public class TransactionPresenter implements TransactionPresenterInterface {
    private final TransactionMapper transactionMapper;

    public TransactionPresenter(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public DepositResponse presentDeposit(DomainTransaction tx, DomainBankAccount account) {
        return this.transactionMapper.toDepositResponse(tx, account);
    }

    @Override
    public WithdrawResponse presentWithdraw(DomainTransaction tx, DomainBankAccount account) {
        return this.transactionMapper.toWithdrawResponse(tx, account);
    }

    @Override
    public TransferResponse presentTransfer(DomainTransaction tx, DomainBankAccount account, DomainBankAccount destinationAccount) {
        return this.transactionMapper.toTransferResponse(tx, account, destinationAccount);
    }
}
