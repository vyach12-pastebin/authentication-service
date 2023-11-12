package com.pastbin.authentication.service;

import com.pastbin.authentication.dto.exception.ErrorCode;
import com.pastbin.authentication.dto.exception.UserNotFoundException;
import com.pastbin.authentication.model.UnverifiedUser;
import com.pastbin.authentication.repository.UnverifiedUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UnverifiedUserService {
    private final UnverifiedUserRepository unverifiedUserRepository;

    public boolean existsByEmail(String email) {
        return unverifiedUserRepository.existsUnverifiedUserByEmail(email);
    }

    public UnverifiedUser findByEmail(String email) {
        return unverifiedUserRepository.findUnverifiedUserByEmail(email).orElseThrow(() ->
                UserNotFoundException.builder()
                        .msg("User with this email does not exists")
                        .instant(Instant.now())
                        .objectCausedException(email)
                        .errorCode(ErrorCode.USER_NOT_FOUND)
                        .build());
    }

    public UnverifiedUser save(UnverifiedUser unverifiedUser) {
        return unverifiedUserRepository.save(unverifiedUser);
    }

    public void delete(UnverifiedUser unverifiedUser) {
        unverifiedUserRepository.delete(unverifiedUser);
    }
}
