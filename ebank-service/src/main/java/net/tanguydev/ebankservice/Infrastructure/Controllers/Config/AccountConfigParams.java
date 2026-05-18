package net.tanguydev.ebankservice.Infrastructure.Controllers.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account.params")
public record AccountConfigParams(int x, int y) {
}
