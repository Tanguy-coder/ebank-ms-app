package net.tanguydev.ebankservice.Infrastructure.Feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.tanguydev.ebankservice.Domain.Entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer-service")
public interface CustomerRestClient {
    @GetMapping("/api/v1/customers/{id}")
    @CircuitBreaker(name = "customer-service", fallbackMethod = "fallbackGetCustomerById")
    Customer getCustomerById(@PathVariable  Long id);

    default Customer fallbackGetCustomerById(Long id, Exception e){
        return  new Customer(id, "Not Available", "Not Available");
    }
}
