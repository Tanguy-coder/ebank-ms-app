package net.tanguydev.gatewayservice.Controller;

import net.tanguydev.gatewayservice.Config.GatewayConfigParam;
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

    private final GatewayConfigParam gatewayConfigParam;

    public ConfigTestRestController(GatewayConfigParam gatewayConfigParam) {
        this.gatewayConfigParam = gatewayConfigParam;
    }


    @RequestMapping("/gateway-config-test1")
    public Map<String, String> configTest() {
        return Map.of("p1", p1, "p2", p2);
    }

    @RequestMapping("/gateway-config-test2")
    public GatewayConfigParam configTest2() {
        return gatewayConfigParam;
    }
}
