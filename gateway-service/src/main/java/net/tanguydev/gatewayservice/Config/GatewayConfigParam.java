package net.tanguydev.gatewayservice.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gateway.params")
public record GatewayConfigParam(int x, int y) {
}
