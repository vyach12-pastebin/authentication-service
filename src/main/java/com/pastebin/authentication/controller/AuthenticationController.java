package com.pastebin.authentication.controller;

import com.pastebin.authentication.dto.AuthenticationResponse;
import com.pastebin.authentication.dto.MessageResponse;
import com.pastebin.authentication.dto.RegisterRequest;
import com.pastebin.authentication.model.User;
import com.pastebin.authentication.service.JwtService;
import com.pastebin.authentication.service.UserService;
import com.pastebin.authentication.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return userService.findById(id);
    }
}
