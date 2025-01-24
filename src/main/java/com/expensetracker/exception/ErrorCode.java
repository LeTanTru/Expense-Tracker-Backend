package com.expensetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_EXISTED(1001, "User already existed !"),
    USERNAME_EXISTED(1002, "Username already existed !"),
    EMAIL_EXISTED(1003, "Email already existed !"),
    PHONE_NUMBER_EXISTED(1004, "Phone number already existed !"),
    USERNAME_INVALID(1005, "Username must be at least 4 characters !"),
    EMAIL_INVALID(1006, "Email should be valid !"),
    PASSWORD_INVALID(1007, "Password must be at least 8 characters !"),
    PHONE_NUMBER_INVALID(1007, "Phone number must be exactly 10 digits!"),
    USER_NOT_FOUND(1008, "User not found !"),
    USER_NOT_EXIST(1009, "User not exist !"),
    UNKNOWN_ERROR(9999, "Unknown error !");

    private final int code;
    private final String message;

}
