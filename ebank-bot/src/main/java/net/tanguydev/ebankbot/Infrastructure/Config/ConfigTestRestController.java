package net.tanguydev.ebankbot.Infrastructure.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RefreshScope
public class ConfigTestRestController {
    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;

    private final BotConfigParams botConfigParams;

    public ConfigTestRestController(BotConfigParams botConfigParams) {
        this.botConfigParams = botConfigParams;
    }

    @RequestMapping("/bot-config-test1")
    public Map<String, String> configTest1() {
        return Map.of("p1", p1, "p2", p2);
    }

    @RequestMapping("/bot-config-test2")
    public BotConfigParams configTest2() {
        return botConfigParams;
    }

}
