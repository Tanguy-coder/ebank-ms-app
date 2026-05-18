package net.tanguydev.ebankservice.Domain.UseCases;

import net.tanguydev.ebankservice.Domain.Entities.DomainTransaction;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.MakeDepositUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MakeDepositUseCaseTest {
    @Mock
    private TransactionServiceInterface transactionService;

    @InjectMocks
    private MakeDepositUseCase makeDepositUseCase;

    @BeforeEach
    void setUp() {MockitoAnnotations.openMocks(this);}

    @Test
    public void makeDepositUseCase_success() {
        //Given

    }
}
