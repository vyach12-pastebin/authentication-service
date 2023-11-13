package com.pastebin.authentication.service;

import com.pastebin.authentication.model.User;
import org.springframework.http.ResponseCookie;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtService {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    ResponseCookie generateRefreshTokenCookie(String token);
    boolean isTokenValid(Jwt token);
}
