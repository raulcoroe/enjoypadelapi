package com.martin.enjoypadelapi.exception;

public class CourtNotFoundException extends Exception {
    private final static String DEFAULT_MESSAGE = "Court not found";

    public CourtNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public CourtNotFoundException(String message) {
        super(message);
    }
}
