package net.tanguydev.ebankservice.Infrastructure.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import net.tanguydev.ebankservice.Domain.Entities.Customer;
import net.tanguydev.ebankservice.Domain.Enums.AccountType;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private Long customerId;
    @Transient
    private Customer customer;
}
