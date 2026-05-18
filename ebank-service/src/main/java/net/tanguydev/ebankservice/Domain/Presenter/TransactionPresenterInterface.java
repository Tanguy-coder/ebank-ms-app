package net.tanguydev.ebankservice.Domain.Presenter;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Response.DepositResponse;
import net.tanguydev.ebankservice.Domain.Response.TransferResponse;
import net.tanguydev.ebankservice.Domain.Response.WithdrawResponse;

public interface TransactionPresenterInterface {
    DepositResponse presentDeposit(DomainTransaction tx, DomainBankAccount account);
    WithdrawResponse presentWithdraw(DomainTransaction tx, DomainBankAccount account);
    TransferResponse presentTransfer(DomainTransaction tx, DomainBankAccount account, DomainBankAccount destinationAccount);
}
