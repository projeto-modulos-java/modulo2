package com.modulo2.auth.controllers.dto;

public record LoginRequest (
    String email,
    String password
) {}
