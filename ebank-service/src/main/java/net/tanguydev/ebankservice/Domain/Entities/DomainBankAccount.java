package net.tanguydev.ebankservice.Domain.Entities;


import net.tanguydev.ebankservice.Domain.Enums.AccountType;
import lombok.*;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DomainBankAccount {
    private String id;
    private Date createdAt;
    private Double balance;
    private AccountType type;
    private Long customerId;
    private Customer customer;

    public void withdraw(Double amount) {
        if (this.balance < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        this.balance -= amount;
    }
}
