package net.tanguydev.ebankservice.Domain.Response;

import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;

import java.time.LocalDateTime;

public class TransferResponse {
    private String transactionId;
    private Double amount;
    private String sourceAccountId;
    private String destinationAccountId;
    private TransactionStatus status;
    private LocalDateTime createdAt;
}
