package com.example.enjoypadelapi.exception;

public class UserNotFoundException extends Exception{

    private final static String DEFAULT_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public UserNotFoundException(String message) {
        super(message);
    }

}
