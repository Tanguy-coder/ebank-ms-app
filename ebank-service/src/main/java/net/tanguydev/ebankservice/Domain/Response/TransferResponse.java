package net.tanguydev.ebankservice.Domain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;
import net.tanguydev.ebankservice.Domain.Entities.TransactionType;

import java.time.LocalDateTime;

public class TransferResponse {
    private String transactionId;
    private Double amount;
    private String sourceAccountId;
    private String destinationAccountId;
    private Double fromBalance ;
    private Double toBalance;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public TransferResponse() {
        super();
    }

    public TransferResponse(String transactionId, Double amount, String sourceAccountId, String destinationAccountId, Double fromBalance, Double toBalance, TransactionType type, TransactionStatus status, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.fromBalance = fromBalance;
        this.toBalance = toBalance;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public Double getFromBalance() {
        return fromBalance;
    }

    public void setFromBalance(Double fromBalance) {
        this.fromBalance = fromBalance;
    }

    public Double getToBalance() {
        return toBalance;
    }

    public void setToBalance(Double toBalance) {
        this.toBalance = toBalance;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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
