package net.tanguydev.customerservice.Infrastructure.Repositories;

import net.tanguydev.customerservice.Infrastructure.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
    Customer findByEmailContainingIgnoreCase(String email);
}
