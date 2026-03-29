package net.tanguydev.ebankservice.Infrastructure.Repositories;

import net.tanguydev.ebankservice.Infrastructure.Models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepositoryInterface extends JpaRepository<BankAccount, String> {
    BankAccount findByCustomerId(Long customerId);
}
