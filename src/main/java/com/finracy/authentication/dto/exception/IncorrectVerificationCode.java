package com.finracy.authentication.dto.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class IncorrectVerificationCode extends BaseException{
    @Builder
    public IncorrectVerificationCode(String msg, Instant instant, Object objectCausedException, ErrorCode errorCode) {
        super(msg, instant, objectCausedException, errorCode);
    }
}
