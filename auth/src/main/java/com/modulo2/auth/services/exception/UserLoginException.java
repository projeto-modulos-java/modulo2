package com.modulo2.auth.services.exception;

public class UserLoginException extends Exception{
    private final String message;

    private static final int STATUS = 401;

    public UserLoginException(String message){
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
