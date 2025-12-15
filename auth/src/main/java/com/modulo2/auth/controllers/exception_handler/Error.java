package com.modulo2.auth.controllers.exception_handler;

import java.time.LocalDateTime;

public class Error {
    private String message;
    private LocalDateTime date = LocalDateTime.now();

    public Error(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
