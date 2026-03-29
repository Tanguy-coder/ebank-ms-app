package net.tanguydev.ebankservice.Domain.Response;

import java.util.Date;

public class WithdrawResponse {
    private String transactionId;
    private String accountId;
    private Double amount;
    private Double newBalance;
    private Date createdAt;
}
