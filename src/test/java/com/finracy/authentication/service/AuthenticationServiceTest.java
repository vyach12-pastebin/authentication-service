package com.finracy.authentication.service;

import com.finracy.authentication.dto.RegisterRequest;
import com.finracy.authentication.dto.exception.UserAlreadyExistsException;
import com.finracy.authentication.model.UnverifiedUser;
import com.finracy.authentication.model.User;
import com.finracy.authentication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    UserService userService;
    @Mock
    UnverifiedUserService unverifiedUserService;
    @InjectMocks
    AuthenticationService authenticationService;

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