package com.modulo2.auth.controllers.exception;

public class LoginException extends Exception {
    private final String message;

    private static final int STATUS = 401;

    public LoginException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return STATUS;
    }
}
