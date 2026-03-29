package net.tanguydev.customerservice.Domain.Validation;

import net.tanguydev.customerservice.Domain.Entities.DomainCustomer;
import net.tanguydev.customerservice.Domain.Validation.Exception.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DomainCustomerValidator implements Validator<DomainCustomer> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public void validate(DomainCustomer customer) {
        Map<String, String> errors = new HashMap<>();

        if (customer.getFullName() == null || customer.getFullName().trim().isEmpty()) {
            errors.put("fullName", "full name is required");
        }

        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            errors.put("email", "email is required");
        } else if (!EMAIL_PATTERN.matcher(customer.getEmail()).matches()) {
            errors.put("email", "email is not valid");
        }

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().isEmpty()) {
            errors.put("phoneNumber", "phone number is required");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
