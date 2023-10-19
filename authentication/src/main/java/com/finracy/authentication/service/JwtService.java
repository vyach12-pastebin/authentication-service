package com.finracy.authentication.service;

import com.finracy.authentication.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.refresh-token-name}")
    private String refreshTokenName;

    @Value("${jwt.access-token-expire-time-ms}")
    @Getter
    private Integer accessTokenExpireTimeMs;

    @Value("${jwt.refresh-token-expire-time-ms}")
    @Getter
    private Integer refreshTokenExpireTimeMs;

    private final JwtEncoder jwtEncoder;

    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        return jwtEncoder.encode(JwtEncoderParameters.from(
                generateClaims(user, now, now.plus(accessTokenExpireTimeMs, ChronoUnit.SECONDS)))).getTokenValue();
    }

    public String generateRefreshToken(User user) {
        Instant now = Instant.now();
        return jwtEncoder.encode(JwtEncoderParameters.from(
                generateClaims(user, now, now.plus(refreshTokenExpireTimeMs, ChronoUnit.SECONDS)))).getTokenValue();
    }

    private JwtClaimsSet generateClaims(User user, Instant issuedAt, Instant expiresAt) {
        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .subject(String.valueOf(user.getId()))
                .claim("scope", scope)
                .build();
    }

    public ResponseCookie generateRefreshTokenCookie(String token) {
        return ResponseCookie.from(refreshTokenName, token)
                .path("/api/v1/auth")
                .maxAge(60 * 60 * 24)
                .httpOnly(true)
                .secure(true)
                .build();
    }

    public boolean isTokenValid(Jwt token) {
        Instant instant = token.getExpiresAt();
        return instant != null && instant.isAfter(Instant.now());
    }
}
