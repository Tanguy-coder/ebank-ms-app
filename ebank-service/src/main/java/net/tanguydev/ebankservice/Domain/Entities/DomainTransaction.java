package net.tanguydev.ebankservice.Domain.Entities;

import java.time.LocalDateTime;

public class DomainTransaction {
    private String id;
    private TransactionType type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private double amount;
    private String sourceAccountId;
    private String destinationAccountId; // null pour dépôt/retrait
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public DomainTransaction() {
        super();
    }

    public DomainTransaction(String id, TransactionType type, double amount, String sourceAccountId, String destinationAccountId, TransactionStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
