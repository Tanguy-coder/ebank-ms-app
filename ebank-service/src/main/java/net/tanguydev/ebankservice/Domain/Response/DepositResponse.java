package net.tanguydev.ebankservice.Domain.Response;

import java.time.LocalDateTime;

public class DepositResponse {
    private String transactionId;
    private String accountId;
    private Double amount;
    private LocalDateTime createdAt;
    private Double newBalance;
}
