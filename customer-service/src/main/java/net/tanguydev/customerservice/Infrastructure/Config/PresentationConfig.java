package net.tanguydev.customerservice.Infrastructure.Config;

import net.tanguydev.customerservice.Infrastructure.Mapper.CustomerMapper;
import net.tanguydev.customerservice.Infrastructure.Presenter.CustomerPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PresentationConfig {
    @Bean
    public CustomerPresenter customerPresenter(CustomerMapper mapper) {
        return new CustomerPresenter(mapper);
    }
}
