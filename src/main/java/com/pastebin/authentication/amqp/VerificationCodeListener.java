package com.pastebin.authentication.amqp;

import com.pastebin.authentication.dto.VerificationCodeDTO;
import com.pastebin.authentication.model.UnverifiedUser;
import com.pastebin.authentication.service.UnverifiedUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class VerificationCodeListener {

    private final UnverifiedUserService unverifiedUserService;

}
