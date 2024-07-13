package com.thymeleafspringbootapplication.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
