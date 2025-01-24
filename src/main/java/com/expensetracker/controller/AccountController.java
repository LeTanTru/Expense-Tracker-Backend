package com.expensetracker.controller;

import com.expensetracker.dto.request.PasswordForgotRequest;
import com.expensetracker.dto.response.ApiResponse;
import com.expensetracker.entity.User;
import com.expensetracker.repository.UserRepository;
import com.expensetracker.utils.BCryptEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {
    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/forgot-password")
    public ApiResponse<String> forgotPassword(@RequestBody PasswordForgotRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email not existed !"));

        String password = request.getPassword().trim();
        String confirmPassword = request.getPassword().trim();

        if (!password.equals(confirmPassword)) {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Confirm password does not match!")
                    .build();
        }

        user.setPassword(BCryptEncoder.encode(password));
        userRepository.save(user);

        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("Reset password successfully!")
                .build();
    }
}
