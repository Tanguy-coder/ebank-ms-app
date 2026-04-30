package net.tanguydev.customerservice.Infrastructure.Controllers.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RefreshScope // Instantiate the controller each time the config is changed
public class ConfigTesRestController {
    @Value("${global.params.p1}")
    private String p1;
    @Value("${global.params.p2}")
    private String p2;

    private final CustomerConfigParams customerConfigParams;

    public ConfigTesRestController(CustomerConfigParams customerConfigParams) {
        this.customerConfigParams = customerConfigParams;
    }
// Manuel config test
    @GetMapping("/customer-config-test1")
    public Map<String, String> configTest() {
        return Map.of("p1", p1, "p2", p2);
    }
// Cloud config test
    @GetMapping("/customer-config-test2")
    public CustomerConfigParams configTest2() {
        return customerConfigParams;
    }
}
