package com.finracy.authentication.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {
    private String msg;
    private Instant instant;
    private Object objectCausedException;
    private ErrorCode errorCode;
}
