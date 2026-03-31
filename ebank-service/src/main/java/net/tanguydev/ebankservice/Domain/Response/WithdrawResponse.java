package net.tanguydev.ebankservice.Domain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WithdrawResponse {
    private String transactionId;
    private String accountId;
    private TransactionStatus status;
    private Double amount;
    private Double newBalance;
    private Date createdAt;
}
