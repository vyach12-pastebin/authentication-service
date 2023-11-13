package com.pastebin.authentication.service;

import com.pastebin.authentication.dto.RegisterRequest;
import com.pastebin.authentication.dto.exception.UserAlreadyExistsException;
import com.pastebin.authentication.model.User;
import com.pastebin.authentication.repository.UserRepository;
import com.pastebin.authentication.service.impl.AuthenticationServiceImpl;
import com.pastebin.authentication.service.impl.UnverifiedUserServiceImpl;
import com.pastebin.authentication.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthenticationServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserServiceImpl userService;
    @Mock
    UnverifiedUserServiceImpl unverifiedUserServiceImpl;
    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("Test throw exception if user already exists")
    void AuthenticationService_Register_ThrowExceptionIfUserAlreadyExists() {
        RegisterRequest request = RegisterRequest.builder()
                .email("email@email")
                .password("123")
                .build();

        when(userService.existsByEmail(any(String.class))).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authenticationService.register(request));
    }

    @Test
    void authenticate() {
    }

    @Test
    void confirmURL() {
    }

    User createUser() {
        return User.builder()
                .id("123")
                .email("email")
                .password("123")
                .createdAt(Instant.now())
                .build();
    }
}