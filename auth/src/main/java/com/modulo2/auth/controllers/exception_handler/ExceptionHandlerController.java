package com.modulo2.auth.controllers.exception_handler;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.modulo2.auth.controllers.exception.LoginException;
import com.modulo2.auth.services.exception.UserLoginException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<Object> handleException(UserLoginException exception){
        return ResponseEntity.status(exception.getStatus()).body(new Error(exception.getMessage(), LocalDateTime.now()));
    }

     @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException exception){
        return ResponseEntity.status(exception.getStatus()).body(new Error(exception.getMessage(), LocalDateTime.now()));
    }

}
