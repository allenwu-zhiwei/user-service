package com.nusiss.userservice.config;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}