package net.tanguydev.ebankservice.Domain.Validation.Exception;

public class AccountNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Account not found";
    public AccountNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
