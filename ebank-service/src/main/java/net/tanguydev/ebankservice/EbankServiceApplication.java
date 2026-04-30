package net.tanguydev.ebankservice;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import net.tanguydev.ebankservice.Domain.Gateways.AccountRepositoryInterface;
import net.tanguydev.ebankservice.Domain.Ports.AccountServiceInterface;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByIdUseCase;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountsUseCase;
import net.tanguydev.ebankservice.Domain.UseCases.Account.CreateAccountUseCase;
import net.tanguydev.ebankservice.Domain.UseCases.Account.GetAccountByCustomerIdUseCase;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.MakeDepositUseCase;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.MakeWithdrawUseCase;
import net.tanguydev.ebankservice.Domain.UseCases.Transactions.TransfertUseCase;
import net.tanguydev.ebankservice.Infrastructure.Adapters.AccountService;
import net.tanguydev.ebankservice.Infrastructure.Controllers.Config.AccountConfigParams;
import net.tanguydev.ebankservice.Infrastructure.Mappers.AccountMapper;
import net.tanguydev.ebankservice.Infrastructure.Mappers.TransactionMapper;
import net.tanguydev.ebankservice.Infrastructure.Presenter.AccountPresenter;
import net.tanguydev.ebankservice.Domain.Ports.TransactionServiceInterface;
import net.tanguydev.ebankservice.Infrastructure.Adapters.TransactionService;
import net.tanguydev.ebankservice.Infrastructure.Presenter.TransactionPresenter;
import net.tanguydev.ebankservice.Infrastructure.Repositories.TransactionRepository;
import net.tanguydev.ebankservice.Infrastructure.Repositories.TransactionJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(AccountConfigParams.class)
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner createTestData(AccountService accountService) {
        return args -> {
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < 5; j++) {
                    // Builds a bank account with random balance and type
                    accountService.save(DomainBankAccount.builder()
                            .balance(1000.0 + Math.random() * 6000)
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .customerId((long) i)
                            .build());
                }
            }
        };
    }

    @Bean
    public AccountPresenter accountPresenter(AccountMapper mapper) {
        return new AccountPresenter(mapper);
    }

    @Bean
    public TransactionPresenter transactionPresenter(TransactionMapper mapper) {
        return new TransactionPresenter(mapper);
    }

    @Bean
    public GetAccountByIdUseCase getAccountByIdUseCase(AccountServiceInterface accountService) {
        return new GetAccountByIdUseCase(accountService);
    }

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountServiceInterface accountService) {
        return new CreateAccountUseCase(accountService);
    }

    @Bean
    public GetAccountsUseCase accountsUseCase(AccountServiceInterface accountService) {
        return new GetAccountsUseCase(accountService);
    }

    @Bean
    public GetAccountByCustomerIdUseCase getAccountByAccountByCustomerId(AccountServiceInterface accountService) {
        return new GetAccountByCustomerIdUseCase(accountService);
    }

    @Bean
    public MakeWithdrawUseCase makeWithdrawUseCase(TransactionServiceInterface transactionService) {
        return new MakeWithdrawUseCase(transactionService);
    }

    @Bean
    public MakeDepositUseCase makeDepositUseCase(TransactionServiceInterface transactionService) {
        return new MakeDepositUseCase(transactionService);
    }

    @Bean
    public TransfertUseCase transfertUseCase(TransactionServiceInterface transactionService) {
        return new TransfertUseCase(transactionService);
    }

}
