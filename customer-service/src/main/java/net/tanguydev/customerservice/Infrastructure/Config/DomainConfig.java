package net.tanguydev.customerservice.Infrastructure.Config;

import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import net.tanguydev.customerservice.Domain.UseCases.CreateCustomerUseCase;
import net.tanguydev.customerservice.Domain.UseCases.GetCustomerByIdUseCase;
import net.tanguydev.customerservice.Domain.UseCases.ListCustomersUseCase;
import net.tanguydev.customerservice.Domain.UseCases.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {
    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(CustomerServiceInterface customerServiceInterface) {
        return new UpdateCustomerUseCase(customerServiceInterface);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerServiceInterface customerServiceInterface) {
        return new CreateCustomerUseCase(customerServiceInterface);
    }

    @Bean
    public ListCustomersUseCase listCustomersUseCase(CustomerServiceInterface customerServiceInterface) {
        return new ListCustomersUseCase(customerServiceInterface);
    }

    @Bean
    public GetCustomerByIdUseCase getCustomerByIdUseCase(CustomerServiceInterface customerServiceInterface) {
        return new GetCustomerByIdUseCase(customerServiceInterface);
    }
}
