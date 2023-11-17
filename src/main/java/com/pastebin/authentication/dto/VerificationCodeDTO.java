package com.pastebin.authentication.dto;

public record VerificationCodeDTO(
        String email,
        String verificationCode
) {
}
