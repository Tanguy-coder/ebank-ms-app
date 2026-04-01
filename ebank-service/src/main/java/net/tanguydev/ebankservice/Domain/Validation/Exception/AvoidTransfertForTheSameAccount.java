package net.tanguydev.ebankservice.Domain.Validation.Exception;

public class AvoidTransfertForTheSameAccount extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "You cannot transfer money to the same account";
    public AvoidTransfertForTheSameAccount() {
        super(DEFAULT_MESSAGE);
    }
}
