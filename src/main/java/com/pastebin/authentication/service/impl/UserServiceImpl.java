package com.pastebin.authentication.service.impl;

import com.pastebin.authentication.dto.exception.ErrorCode;
import com.pastebin.authentication.dto.exception.UserNotFoundException;
import com.pastebin.authentication.model.User;
import com.pastebin.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.pastebin.authentication.service.UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findUserById(id).orElseThrow(() -> UserNotFoundException.builder()
                .msg("User with this id does not exists")
                .instant(Instant.now())
                .objectCausedException(id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .build());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> UserNotFoundException.builder()
                .msg("User with this email does not exists")
                .instant(Instant.now())
                .objectCausedException(email)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .build());
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
