package com.finracy.authentication.dto;

public record RegisterRequest(
        String email,
        String password
) {
}
