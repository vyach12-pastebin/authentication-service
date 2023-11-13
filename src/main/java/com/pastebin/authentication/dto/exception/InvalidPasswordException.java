package com.pastebin.authentication.dto.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
public class InvalidPasswordException extends BaseException{
    @Builder
    public InvalidPasswordException(String msg, Instant instant, Object objectCausedException, ErrorCode errorCode) {
        super(msg, instant, objectCausedException, errorCode);
    }
}
