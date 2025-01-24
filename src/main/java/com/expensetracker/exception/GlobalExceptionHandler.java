package com.expensetracker.exception;

import com.expensetracker.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<RuntimeException>> handleRuntimeException(RuntimeException e) {
        ApiResponse<RuntimeException> apiResponse =
                ApiResponse.<RuntimeException>builder()
                        .code(ErrorCode.UNKNOWN_ERROR.getCode())
                        .message(ErrorCode.UNKNOWN_ERROR.getMessage() + ". " + e.getMessage())
                        .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<RuntimeException>> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<RuntimeException> apiResponse =
                ApiResponse.<RuntimeException>builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<RuntimeException>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();

        ErrorCode error = ErrorCode.valueOf(enumKey);
        ApiResponse<RuntimeException> apiResponse =
                ApiResponse.<RuntimeException>builder()
                        .code(error.getCode())
                        .message(error.getMessage())
                        .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
