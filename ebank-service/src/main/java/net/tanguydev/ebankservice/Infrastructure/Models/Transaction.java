package net.tanguydev.ebankservice.Infrastructure.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import net.tanguydev.ebankservice.Domain.Entities.TransactionStatus;
import net.tanguydev.ebankservice.Domain.Entities.TransactionType;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {
    @Id
    private String id;
    private TransactionType type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private double amount;
    private String sourceAccountId;
    private String destinationAccountId; // null pour dépôt/retrait
    private TransactionStatus status;
    private LocalDateTime createdAt;
}
