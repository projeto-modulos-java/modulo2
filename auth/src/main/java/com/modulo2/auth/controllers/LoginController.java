package com.modulo2.auth.controllers;

import java.util.Date;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modulo2.auth.configurations.security.JWTProvider;
import com.modulo2.auth.controllers.adapters.UserDTOAdapter;
import com.modulo2.auth.controllers.dto.LoginRequest;
import com.modulo2.auth.controllers.dto.LoginResponse;
import com.modulo2.auth.controllers.dto.UserRequest;
import com.modulo2.auth.controllers.dto.UserResponse;
import com.modulo2.auth.services.AuthService;
import com.modulo2.auth.services.exception.UserLoginException;

@RestController
@RequestMapping(path = "/auth")
public class LoginController {
    private AuthService service;
    private final UserDTOAdapter adapter;
    private final JWTProvider jwtProvider;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(AuthService service, UserDTOAdapter adapter, JWTProvider jwtProvider) {
        this.service = service;
        this.adapter = adapter;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws UserLoginException, LoginException{
        int hours = (2 * 3600 * 1000);
        Date expires = new Date((new Date()).getTime() + hours);

        UserResponse user = adapter.adaptResponse(service.login(request.email(), request.password()));

        String token = this.jwtProvider.createToken(user.id().toString(), expires);

        var loginResponse = new LoginResponse(token, expires.toString());

        logger.info("Login realizado com sucesso, usuario: {}", user.id());

        return ResponseEntity.ok(loginResponse);

    }

    @PostMapping(path = "/cadastro")
    public ResponseEntity<Object> create(@RequestBody UserRequest user) throws UserLoginException{
        this.service.create(adapter.adaptUser(user));
        logger.info("Cadastro realizado com sucesso, usuario: {}", user.email());
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/me")
    public String get() throws LoginException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            throw new LoginException("Usuario nao autenticado");
        } else {
             return authentication.getName();
        }

    }
}
