package com.martin.enjoypadelapi.exception;

public class TeamNotFoundException extends Exception {
    private final static String DEFAULT_MESSAGE = "Team not found";

    public TeamNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public TeamNotFoundException(String message) {
        super(message);
    }
}
