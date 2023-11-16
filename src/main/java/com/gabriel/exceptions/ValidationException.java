package com.gabriel.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {
    List<String> validations;

    public ValidationException(final String message, final List<String> validations) {
        super(message);
        this.validations = validations;
    }

    public List<String> getValidations() {
        return validations;
    }

    public ValidationException setValidations(List<String> validations) {
        this.validations = validations;
        return this;
    }
}
