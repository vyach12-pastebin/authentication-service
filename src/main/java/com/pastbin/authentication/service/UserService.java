package com.pastbin.authentication.service;

import com.pastbin.authentication.dto.exception.ErrorCode;
import com.pastbin.authentication.dto.exception.UserNotFoundException;
import com.pastbin.authentication.model.User;
import com.pastbin.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(String id) {
        return userRepository.findUserById(id).orElseThrow(() -> UserNotFoundException.builder()
                .msg("User with this id does not exists")
                .instant(Instant.now())
                .objectCausedException(id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .build());
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> UserNotFoundException.builder()
                .msg("User with this email does not exists")
                .instant(Instant.now())
                .objectCausedException(email)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .build());
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
