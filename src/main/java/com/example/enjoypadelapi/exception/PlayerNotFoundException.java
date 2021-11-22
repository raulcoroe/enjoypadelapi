package com.example.enjoypadelapi.exception;

public class PlayerNotFoundException extends Exception{

    private final static String DEFAULT_MESSAGE = "Player not found";

    public PlayerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    public PlayerNotFoundException(String message) {
        super(message);
    }

}
