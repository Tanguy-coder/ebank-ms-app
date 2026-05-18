package net.tanguydev.ebankbot.Infrastructure.Controllers;

import net.tanguydev.ebankbot.Infrastructure.Agents.EbankAIAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class EbankChatBotController {
    private EbankAIAgent ebankAIAgent;
    public EbankChatBotController(EbankAIAgent ebankAIAgent) {
        this.ebankAIAgent = ebankAIAgent;
    }
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "query", defaultValue = "Bonjour") String query) {
        return ebankAIAgent.chat(query);
    }

    @GetMapping("/chatStream")
    public Flux<String> chatStream(@RequestParam(value = "query", defaultValue = "Bonjour") String query) {
        return ebankAIAgent.chatStream(query);
    }

}
