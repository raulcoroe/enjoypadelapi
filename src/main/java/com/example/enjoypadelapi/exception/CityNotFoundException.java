package com.example.enjoypadelapi.exception;

public class CityNotFoundException extends Exception {
    private final static String DEFAULT_MESSAGE = "City not found";

    public CityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public CityNotFoundException(String message) {
        super(message);
    }
}
