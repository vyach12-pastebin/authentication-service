package com.finracy.authentication.controller;

import com.finracy.authentication.dto.AuthenticationResponse;
import com.finracy.authentication.dto.MessageResponse;
import com.finracy.authentication.dto.RegisterRequest;
import com.finracy.authentication.model.User;
import com.finracy.authentication.service.AuthenticationService;
import com.finracy.authentication.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public MessageResponse register(@Validated @RequestBody RegisterRequest request) {
        authenticationService.register(request);
        return MessageResponse.withMessage("Verify your account using the link sent to your email");
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@Validated @RequestBody RegisterRequest request) {
        User user = authenticationService.authenticate(request);
        return AuthenticationResponse.builder()
                .accessToken(jwtService.generateAccessToken(user)).build();
    }

    @GetMapping("/confirm")
    public void confirmUrl(@RequestParam("email") String email, @RequestParam("code") String code) {
        log.info("email: {}",email);
        log.info("code: {}",code);

        authenticationService.confirmURL(email, code);
    }
}
