package com.pastebin.authentication.service.impl;

import com.pastebin.authentication.dto.exception.ErrorCode;
import com.pastebin.authentication.dto.exception.UserNotFoundException;
import com.pastebin.authentication.model.UnverifiedUser;
import com.pastebin.authentication.repository.UnverifiedUserRepository;
import com.pastebin.authentication.service.UnverifiedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UnverifiedUserServiceImpl implements UnverifiedUserService {
    private final UnverifiedUserRepository unverifiedUserRepository;

    @Override
    public boolean existsByEmail(String email) {
        return unverifiedUserRepository.existsUnverifiedUserByEmail(email);
    }


    @Override
    public UnverifiedUser findByEmail(String email) {
        return unverifiedUserRepository.findUnverifiedUserByEmail(email).orElseThrow(() ->
                UserNotFoundException.builder()
                        .msg("User with this email does not exists")
                        .instant(Instant.now())
                        .objectCausedException(email)
                        .errorCode(ErrorCode.USER_NOT_FOUND)
                        .build());
    }

    @Override
    public UnverifiedUser save(UnverifiedUser unverifiedUser) {
        return unverifiedUserRepository.save(unverifiedUser);
    }

    @Override
    public void delete(UnverifiedUser unverifiedUser) {
        unverifiedUserRepository.delete(unverifiedUser);
    }
}
