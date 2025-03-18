package com.innowise.UserService.service.validation;

import com.innowise.UserService.exceptions.ValidationException;

public class PasswordValidator {

    public static void validatePassword(String password) {
        if (password == null) {
            throw new ValidationException("Password is required");
        }

        if (password.length() < ValidationConstants.MIN_PASSWORD_LENGTH) {
            throw new ValidationException("Password must be at least " + ValidationConstants.MIN_PASSWORD_LENGTH
                    + " characters");
        }

        if (!password.matches(ValidationConstants.PASSWORD_PATTERN)) {
            throw new ValidationException("Password must contain at least one uppercase letter, " +
                    "one lowercase letter, and one special character");
        }
    }
}
