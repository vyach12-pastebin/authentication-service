package com.pastebin.authentication.controller;

import com.pastebin.authentication.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthenticationControllerTest {

    @Mock
    AuthenticationServiceImpl authenticationService;

    @InjectMocks
    AuthenticationController authenticationController;

    @Test
    void register() {
        
    }

    @Test
    void authenticate() {
    }

    @Test
    void confirmUrl() {
    }
}