package net.tanguydev.ebankservice.Domain.Validation;

public interface Validator<T> {
    void validate(T target);
}
