package net.tanguydev.ebankservice.Domain.Entities;


import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import lombok.*;
import java.util.Date;
@Builder
public class DomainBankAccount {
    private String id;
    private Date createdAt;
    private Double balance;
    private AccountType type;
    private Long customerId;
    private Customer customer;

    public DomainBankAccount() {
        super();
    }

    public DomainBankAccount(String id, Date createdAt, Double balance, AccountType type, Long customerId, Customer customer) {
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void withdraw(Double amount) {
        if (this.balance < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        this.balance -= amount;
    }

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void transfer(Double amount, DomainBankAccount destinationAccount) {
        withdraw(amount);
        destinationAccount.deposit(amount);
    }
}
