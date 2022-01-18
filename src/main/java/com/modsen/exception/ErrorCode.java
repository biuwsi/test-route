package com.modsen.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN_COUNTRY_CODE("Unable to find country by code", 400),
    PATH_NOT_FOUND("Unable to build path", 400);

    private String text;
    private int httpCode;

}
