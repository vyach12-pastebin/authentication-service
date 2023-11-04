package com.finracy.authentication.repository;

import com.finracy.authentication.AuthenticationApplication;
import com.finracy.authentication.model.User;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureDataMongo
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void init() {
        user = createUser();
    }

    @Test
    void UserRepository_findUserById_returnSavedUser() {
        userRepository.save(user);

        Optional<User> userInDB = userRepository.findUserById(user.getId());

        assertThat(userInDB).isPresent();
        assertThat(userInDB.get()).isEqualTo(user);
    }

    @Test
    void UserRepository_findUserByEmail_returnSavedUser() {
        userRepository.save(user);

        Optional<User> userInDB = userRepository.findUserByEmail(user.getEmail());

        assertThat(userInDB).isPresent();
        assertThat(userInDB.get()).isEqualTo(user);
    }

    @Test
    void UserRepository_existsUserByEmail_returnTrueIfUserExists() {
        userRepository.save(user);

        boolean present = userRepository.existsUserByEmail(user.getEmail());

        assertTrue(present);
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