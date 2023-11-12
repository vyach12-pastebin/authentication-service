package com.pastbin.authentication.service;

import com.pastbin.authentication.dto.RegisterRequest;
import com.pastbin.authentication.dto.exception.ErrorCode;
import com.pastbin.authentication.dto.exception.IncorrectVerificationCode;
import com.pastbin.authentication.dto.exception.InvalidPasswordException;
import com.pastbin.authentication.dto.exception.UserAlreadyExistsException;
import com.pastbin.authentication.model.UnverifiedUser;
import com.pastbin.authentication.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {


    private final EmailSenderService emailSenderService;
    private final UserService userService;
    private final UnverifiedUserService unverifiedUserService;
    private final PasswordEncoder passwordEncoder;

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
        emailSenderService.sendEmail(request.email(), "Your finracy.com verification code", emailBody);
        log.info("email: {}",request.email());
        log.info("code: {}",verificationCode);


        UnverifiedUser unverifiedUser = UnverifiedUser.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .createdAt(Instant.now())
                .verificationCode(verificationCode)
                .build();


        unverifiedUserService.save(unverifiedUser);
    }

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

    public void confirmURL(String email, String code){
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
