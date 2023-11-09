package com.finracy.authentication.service;

import com.finracy.authentication.model.User;
import com.finracy.authentication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;
    @BeforeEach
    void init() {
        user = createUser();
    }

    @Test
    void UserService_FindById_ReturnUser() {
        when(userRepository.findUserById(user.getId())).thenReturn(Optional.of(user));

        User savedUser = userService.findById(user.getId());

        assertThat(savedUser).isNotNull();
    }

    @Test
    void UserService_FindByEmail_ReturnUser() {
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User savedUser = userService.findByEmail(user.getEmail());

        assertThat(savedUser).isNotNull();
    }

    @Test
    void UserService_SaveUser_ReturnSavedUser() {
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.save(user);

        assertThat(savedUser).isNotNull();
    }

    @Test
    void UserService_ExistsByEmail_ReturnTrue() {
        when(userRepository.existsUserByEmail(user.getEmail())).thenReturn(true);

        boolean present = userService.existsByEmail(user.getEmail());

        assertThat(present).isTrue();
    }

    User createUser() {
        return User.builder()
                .id("123")
                .email("email")
                .password("123")
                .createdAt(Instant.ofEpochSecond(1))
                .build();
    }
}