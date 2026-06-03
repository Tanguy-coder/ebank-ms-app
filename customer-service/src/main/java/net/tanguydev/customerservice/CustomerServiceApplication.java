package net.tanguydev.customerservice;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Port.CustomerServiceInterface;
import net.tanguydev.customerservice.Domain.UseCases.CreateCustomerUseCase;
import net.tanguydev.customerservice.Domain.UseCases.GetCustomerByIdUseCase;
import net.tanguydev.customerservice.Domain.UseCases.ListCustomersUseCase;
import net.tanguydev.customerservice.Domain.UseCases.UpdateCustomerUseCase;
import net.tanguydev.customerservice.Infrastructure.Adapters.CustomerService;
import net.tanguydev.customerservice.Infrastructure.Controllers.config.CustomerConfigParams;
import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;
import net.tanguydev.customerservice.Infrastructure.Presenter.CustomerPresenter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    @org.springframework.context.annotation.Profile("!test")
    CommandLineRunner init(CustomerServiceInterface customerService) {
        return args -> {
            // Persists ten default customers if not testing
            for (int i = 0; i < 10; i++) {
                DomainCustomer customer = new DomainCustomer();
                customer.setFullName("Customer " + i);
                customer.setEmail("customer" + i + "@mail.com");
                customer.setPhoneNumber("060000000" + i);
                customerService.save(customer);
            }
        };
    }

}
