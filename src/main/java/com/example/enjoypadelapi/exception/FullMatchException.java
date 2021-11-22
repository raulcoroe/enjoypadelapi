package com.example.enjoypadelapi.exception;

public class FullMatchException extends Exception {
    private final static String DEFAULT_MESSAGE = "The match cannot have more than two teams";

    public FullMatchException() {
        super(DEFAULT_MESSAGE);
    }
    public FullMatchException(String message) {
        super(message);
    }
}
