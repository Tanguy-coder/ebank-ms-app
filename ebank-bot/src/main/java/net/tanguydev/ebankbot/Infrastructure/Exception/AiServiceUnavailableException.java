package net.tanguydev.ebankbot.Infrastructure.Exception;

public class AiServiceUnavailableException extends RuntimeException {
  public AiServiceUnavailableException() {
    super("AI service unavailable");
  }
}
