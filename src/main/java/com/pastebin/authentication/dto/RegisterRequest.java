package com.pastebin.authentication.dto;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String email,
        String password
) {
}
