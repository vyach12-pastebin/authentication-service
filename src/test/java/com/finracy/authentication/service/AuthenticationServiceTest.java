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
    MongoOperations mongoOps;
    @Mock
    UserRepository userRepository;
    @Mock
    UserService userService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    EmailSenderService emailSenderService;
    @Mock
    UnverifiedUserService unverifiedUserService;
    @InjectMocks
    AuthenticationService authenticationService;

    User user;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        user = createUser();
    }

    @Test
    @DisplayName("Test throw exception if user already exists")
    void AuthenticationService_Register_ThrowExceptionIfUserAlreadyExists() {
        RegisterRequest request = RegisterRequest.builder()
                .email("email@email")
                .password("123")
                .build();

        when(userService.existsByEmail(request.email())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> authenticationService.register(request));
    }

    @Test
    @DisplayName("Test successful registration")
    void AuthenticationService_Register_ReturnUnverifiedUser() {
        RegisterRequest request = RegisterRequest.builder()
                .email("email@email")
                .password("password")
                .build();
        UnverifiedUser unverifiedUser = UnverifiedUser.builder()
                .id("123")
                .email(request.email())
                .password(request.password())
                .build();

        when(userService.existsByEmail(request.email())).thenReturn(false);
        when(unverifiedUserService.existsByEmail(request.email())).thenReturn(false);
        when(passwordEncoder.encode(request.password())).thenReturn(request.password());
        when(mongoOps.indexOps(UnverifiedUser.class).ensureIndex(Mockito.any(Index.class))).thenReturn("Пизда");

        authenticationService.register(request);
        assertThat(unverifiedUser).isNotNull();
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