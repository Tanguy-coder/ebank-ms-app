package net.tanguydev.ebankservice.Infrastructure.Controllers;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Response.DepositResponse;
import net.tanguydev.ebankservice.Domain.Response.TransferResponse;
import net.tanguydev.ebankservice.Domain.Response.WithdrawResponse;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByIdUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.MakeDepositUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.MakeWithdrawUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.TransfertUseCaseInterface;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import net.tanguydev.ebankservice.Infrastructure.Presenter.AccountPresenter;
import net.tanguydev.ebankservice.Infrastructure.Presenter.TransactionPresenter;
import net.tanguydev.ebankservice.Infrastructure.Request.DepositRequest;
import net.tanguydev.ebankservice.Infrastructure.Request.TransferRequest;
import net.tanguydev.ebankservice.Infrastructure.Request.WithdrawRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final GetAccountByIdUseCaseInterface getAccountByIdUseCaseInterface;
    private final MakeDepositUseCaseInterface makeDepositUseCase;
    private final MakeWithdrawUseCaseInterface makeWithdrawUseCase;
    private final TransfertUseCaseInterface transfertUseCase;
    private final TransactionPresenter presenter;

    public TransactionController(GetAccountByIdUseCaseInterface getAccountByIdUseCaseInterface, MakeDepositUseCaseInterface makeDepositUseCase, MakeWithdrawUseCaseInterface makeWithdrawUseCase,
                                 TransfertUseCaseInterface transfertUseCase, TransactionPresenter presenter) {
        this.getAccountByIdUseCaseInterface = getAccountByIdUseCaseInterface;
        this.makeDepositUseCase = makeDepositUseCase;
        this.makeWithdrawUseCase = makeWithdrawUseCase;
        this.transfertUseCase = transfertUseCase;
        this.presenter = presenter;
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestBody DepositRequest request) {
        String accountId = request.getAccountId();
        Double amount = request.getAmount();
        DomainTransaction transaction = this.makeDepositUseCase.execute(accountId, amount);
        DomainBankAccount account = this.getAccountByIdUseCaseInterface.execute(accountId);
        return ResponseEntity.ok(presenter.presentDeposit(transaction, account));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawResponse> withdraw(@RequestBody WithdrawRequest request) {
        String accountId = request.getAccountId();
        Double amount = request.getAmount();
        DomainTransaction transaction = this.makeWithdrawUseCase.execute(accountId, amount);
        DomainBankAccount account = this.getAccountByIdUseCaseInterface.execute(accountId);
        return ResponseEntity.ok(presenter.presentWithdraw(transaction, account));
    }

    @PostMapping("/transfert")
    public TransferResponse transfert(@RequestBody TransferRequest request) {
        String fromAccountId = request.getSourceAccountId();
        String toAccountId = request.getDestinationAccountId();
        Double amount = request.getAmount();
        DomainTransaction transaction = this.transfertUseCase.execute(fromAccountId, toAccountId, amount);
        DomainBankAccount fromAccount = this.getAccountByIdUseCaseInterface.execute(fromAccountId);
        DomainBankAccount toAccount = this.getAccountByIdUseCaseInterface.execute(toAccountId);
        return presenter.presentTransfer(transaction, fromAccount, toAccount);
    }
}
