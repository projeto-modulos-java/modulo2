package com.modulo2.auth.controllers.dto;

public record LoginResponse (
    String token,
    String expiresAt
) {}
