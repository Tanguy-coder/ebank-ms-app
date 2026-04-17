package net.tanguydev.ebankbot.Infrastructure.Agents;

import net.tanguydev.ebankbot.Infrastructure.Exception.AiServiceUnavailableException;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

@Service
public class EbankAIAgent {
    private ChatClient chatClient;
    public EbankAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Vous etes un agent qui a pour role de repondre aux questions liees aux clients et aux comptes bancaires.
                        Une question ou requete en dehors de ce contexte est interdit et vous pourrez repondre que vous ne savez pas.
                        Tout context lie au depot, retrait et transfert de fonds est interdit.
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String chat(String query) {
        try {
            return chatClient.prompt(query).call().content();
        } catch (Exception e) {
            throw new AiServiceUnavailableException();
        }
    }

    public Flux<String> chatStream(String query) {
        try {
            return chatClient.prompt(query).stream().content();
        } catch (Exception e) {
            throw new AiServiceUnavailableException();
        }
    }
}
