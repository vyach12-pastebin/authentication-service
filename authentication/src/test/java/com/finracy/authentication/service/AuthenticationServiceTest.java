package com.finracy.authentication.service;

import com.finracy.authentication.model.User;
import com.finracy.authentication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthenticationService authenticationService;


    @Test
    void register_UserAlreadyExists() {
        User user = User.builder()
                .id("123")
                .email("email")
                .password("123")
                .createdAt(Instant.now())
                .build();
    }

    @Test
    void authenticate() {
    }

    @Test
    void confirmURL() {
    }
}