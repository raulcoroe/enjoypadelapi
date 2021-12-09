package com.martin.enjoypadelapi.exception;

public class CenterNotFoundException extends Exception {
    private final static String DEFAULT_MESSAGE = "Center not found";

    public CenterNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public CenterNotFoundException(String message) {
        super(message);
    }
}
