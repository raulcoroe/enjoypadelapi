package com.martin.enjoypadelapi.exception;

public class MatchNotFoundException extends Exception {
    private final static String DEFAULT_MESSAGE = "Match not found";

    public MatchNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public MatchNotFoundException(String message) {
        super(message);
    }
}
