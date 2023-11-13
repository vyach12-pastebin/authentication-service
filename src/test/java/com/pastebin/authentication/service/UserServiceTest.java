package com.pastebin.authentication.service;

import com.pastebin.authentication.model.User;
import com.pastebin.authentication.repository.UserRepository;
import com.pastebin.authentication.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

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