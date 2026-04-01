package net.tanguydev.ebankservice.Domain.Validation.Exception;

public class InsufficientBalanceException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Insufficient balance";
    public InsufficientBalanceException() {
        super(DEFAULT_MESSAGE);
    }
}
