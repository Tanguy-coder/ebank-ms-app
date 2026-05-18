package net.tanguydev.ebankservice.Domain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DepositResponse {
    private String transactionId;
    private TransactionStatus status;
    private String accountId;
    private Double amount;
    private LocalDateTime createdAt;
    private Double newBalance;
}
