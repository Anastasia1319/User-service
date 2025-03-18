package com.innowise.UserService.service.validation;

public class ValidationConstants {

    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{5,}$";
    public static final int MIN_PASSWORD_LENGTH = 5;
}
