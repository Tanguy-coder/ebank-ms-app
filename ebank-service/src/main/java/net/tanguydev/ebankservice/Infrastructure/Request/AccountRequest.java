package net.tanguydev.ebankservice.Infrastructure.Request;

import net.tanguydev.ebankservice.Domain.Enums.AccountType;

import java.util.Date;

public class AccountRequest {
    private AccountType type;
    private Double balance;
    private String customerId;
    private Date createdAt;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
