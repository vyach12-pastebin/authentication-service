package com.pastebin.authentication.service.impl;

import com.pastebin.authentication.amqp.RegisterNotificationProducer;
import com.pastebin.authentication.dto.RegisterNotificationDTO;
import com.pastebin.authentication.dto.RegisterRequest;
import com.pastebin.authentication.dto.exception.ErrorCode;
import com.pastebin.authentication.dto.exception.IncorrectVerificationCode;
import com.pastebin.authentication.dto.exception.InvalidPasswordException;
import com.pastebin.authentication.dto.exception.UserAlreadyExistsException;
import com.pastebin.authentication.model.UnverifiedUser;
import com.pastebin.authentication.model.User;
import com.pastebin.authentication.service.AuthenticationService;
import com.pastebin.authentication.service.UnverifiedUserService;
import com.pastebin.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${spring.rabbitmq.exchanges.register-notification}")
    private String internalExchange;

    @Value("${spring.rabbitmq.routing-keys.register-notification}")
    private String internalRegisterNotificationRoutingKey;

    private final UserService userService;
    private final UnverifiedUserService unverifiedUserService;
    private final PasswordEncoder passwordEncoder;
    private final RegisterNotificationProducer notificationProducer;

    @Override
    public void register(RegisterRequest request) {
        if(userService.existsByEmail(request.email()) || unverifiedUserService.existsByEmail(request.email())) {
            throw UserAlreadyExistsException.builder()
                    .msg("User with this email is already exists")
                    .objectCausedException(request)
                    .instant(Instant.now())
                    .errorCode(ErrorCode.USER_ALREADY_EXIST)
                    .build();
        }

        String verificationCode = UUID.randomUUID().toString();
        String confirmationURL = "http://localhost:8080/api/v1/auth/confirm?code=" + verificationCode + "&email=" + request.email();
        String emailBody = "Для подтверждения аккаунта перейдите по ссылке: " + confirmationURL;

        RegisterNotificationDTO notificationDTO = RegisterNotificationDTO.builder()
                .to(request.email())
                .subject("Your finracy.com verification code")
                .body(emailBody)
                .build();

        notificationProducer.publishEmail(notificationDTO, internalExchange, internalRegisterNotificationRoutingKey);

        UnverifiedUser unverifiedUser = UnverifiedUser.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .createdAt(Instant.now())
                .verificationCode(verificationCode)
                .build();

        unverifiedUserService.save(unverifiedUser);
    }

    @Override
    public User authenticate(RegisterRequest request) {
        User user = userService.findByEmail(request.email());
        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw InvalidPasswordException.builder()
                    .msg("Invalid password")
                    .errorCode(ErrorCode.INVALID_PASSWORD)
                    .objectCausedException(request.email())
                    .instant(Instant.now())
                    .build();
        }

        return user;
    }

    @Override
    public void confirmURL(String email, String code) {
        UnverifiedUser unverifiedUser = unverifiedUserService.findByEmail(email);
        if(!unverifiedUser.getVerificationCode().equals(code)) {
            throw IncorrectVerificationCode.builder()
                    .msg("Incorrect verification code")
                    .errorCode(ErrorCode.INCORRECT_VERIFICATION_CODE)
                    .instant(Instant.now())
                    .objectCausedException(code)
                    .build();
        }

        User user = User.builder()
                .email(unverifiedUser.getEmail())
                .password(unverifiedUser.getPassword())
                .createdAt(unverifiedUser.getCreatedAt())
                .build();

        unverifiedUserService.delete(unverifiedUser);
        userService.save(user);
    }
}
