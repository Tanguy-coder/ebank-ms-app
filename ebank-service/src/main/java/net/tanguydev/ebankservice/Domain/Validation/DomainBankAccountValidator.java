package net.tanguydev.ebankservice.Domain.Validation;

import net.tanguydev.ebankservice.Domain.Entities.DomainBankAccount;
import net.tanguydev.ebankservice.Domain.Validation.Exception.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class DomainBankAccountValidator implements Validator<DomainBankAccount>{
    @Override
    public void validate(DomainBankAccount domainBankAccount) {
        Map<String, String> errors = new HashMap<>();

        if (domainBankAccount.getBalance() < 0) {
            errors.put("balance", "balance must be positive");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
