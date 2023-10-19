package com.finracy.authentication.dto.exception;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

public class CanNotSendEmailException extends BaseException {
    @Builder
    public CanNotSendEmailException(String msg, Instant instant, Object objectCausedException, ErrorCode errorCode) {
        super(msg, instant, objectCausedException, errorCode);
    }
}
