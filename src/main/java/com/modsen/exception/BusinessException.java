package com.modsen.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BusinessException extends RuntimeException {
    @Getter
    private ErrorCode errorCode;
}
