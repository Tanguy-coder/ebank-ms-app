package net.tanguydev.ebankservice.Infrastructure.Repositories;

import net.tanguydev.ebankservice.Infrastructure.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<Transaction, String> {
}
