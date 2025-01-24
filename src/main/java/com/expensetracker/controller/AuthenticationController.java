package com.expensetracker.controller;

import com.expensetracker.dto.request.AuthenticationRequest;
import com.expensetracker.dto.response.ApiResponse;
import com.expensetracker.dto.response.AuthenticationResponse;
import com.expensetracker.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/log-in")
    public ApiResponse<AuthenticationResponse> logIn(@RequestBody AuthenticationRequest request) {
    AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
    return ApiResponse.<AuthenticationResponse>builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(authenticationResponse.isAuthenticated() ? "Log in successfully" : "Wrong password")
            .result(authenticationResponse)
            .build();
    }
}
