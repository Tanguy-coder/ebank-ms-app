package net.tanguydev.ebankservice.Domain.Response;

import net.tanguydev.ebankservice.Domain.Entities.Customer;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;

import java.util.Date;


public class AccountResponse {
    private String id;
    private Date createdAt;
    private Double balance;
    private AccountType type;
    private String customerId;
    private Customer customer;

    public AccountResponse() {
    }

    public AccountResponse(String id, Date createdAt, Double balance, AccountType type, String customerId, Customer customer) {
        this.id = id;
        this.createdAt = createdAt;
        this.balance = balance;
        this.type = type;
        this.customerId = customerId;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
