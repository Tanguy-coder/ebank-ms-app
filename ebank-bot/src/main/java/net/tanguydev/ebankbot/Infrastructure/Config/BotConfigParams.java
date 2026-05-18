package net.tanguydev.ebankbot.Infrastructure.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bot.params")
public record BotConfigParams(int x, int y) {
}
