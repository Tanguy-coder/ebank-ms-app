package net.tanguydev.customerservice.Domain.Validation;

public interface Validator<T> {
    void validate(T target);
}
