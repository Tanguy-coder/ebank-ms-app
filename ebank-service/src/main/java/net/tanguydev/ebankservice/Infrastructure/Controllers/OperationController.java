package net.tanguydev.ebankservice.Infrastructure.Controllers;

import net.tanguydev.ebankservice.Domain.UseCases.Opperations.MakeDepositUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Opperations.MakeWithdrawUseCaseInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Opperations.TransfertUseCaseInterface;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import net.tanguydev.ebankservice.Infrastructure.Presenter.AccountPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    private final MakeDepositUseCaseInterface makeDepositUseCase;
    private final MakeWithdrawUseCaseInterface makeWithdrawUseCase;
    private final TransfertUseCaseInterface transfertUseCase;
    private final AccountMapper mapper;
    private final AccountPresenter presenter;

    public OperationController(MakeDepositUseCaseInterface makeDepositUseCase, MakeWithdrawUseCaseInterface makeWithdrawUseCase, TransfertUseCaseInterface transfertUseCase, AccountMapper mapper, AccountPresenter presenter) {
        this.makeDepositUseCase = makeDepositUseCase;
        this.makeWithdrawUseCase = makeWithdrawUseCase;
        this.transfertUseCase = transfertUseCase;
        this.mapper = mapper;
        this.presenter = presenter;
    }

    public ResponseEntity<Void> deposit(String accountId, Double amount) {
        this.makeDepositUseCase.execute(accountId, amount);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> withdraw(String accountId, Double amount) {
        this.makeWithdrawUseCase.execute(accountId, amount);
        return ResponseEntity.ok().build();
    }
}
