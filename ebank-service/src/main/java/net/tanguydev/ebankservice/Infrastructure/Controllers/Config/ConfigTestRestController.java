package net.tanguydev.ebankservice.Infrastructure.Controllers.Config;

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

    private final AccountConfigParams accountConfigParams;

    public ConfigTestRestController(AccountConfigParams accountConfigParams) {
        this.accountConfigParams = accountConfigParams;
    }

    @RequestMapping("/account-config-test1")
    public Map<String, String> configTest() {
        return Map.of("p1", p1, "p2", p2);
    }

    @RequestMapping("/account-config-test2")
    public AccountConfigParams configTest2() {
        return accountConfigParams;
    }
}
