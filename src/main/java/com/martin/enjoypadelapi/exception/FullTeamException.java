package com.martin.enjoypadelapi.exception;

public class FullTeamException extends Exception {
    private final static String DEFAULT_MESSAGE = "The team cannot have more than two players";

    public FullTeamException() {
        super(DEFAULT_MESSAGE);
    }
    public FullTeamException(String message) {
        super(message);
    }
}
