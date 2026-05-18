package net.tanguydev.ebankbot;

import net.tanguydev.ebankbot.Infrastructure.Config.BotConfigParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BotConfigParams.class)
public class EbankBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBotApplication.class, args);
    }

}
