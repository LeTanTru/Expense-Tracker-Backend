package com.expensetracker.service;

import com.expensetracker.dto.request.AuthenticationRequest;
import com.expensetracker.dto.response.AuthenticationResponse;
import com.expensetracker.exception.AppException;
import com.expensetracker.exception.ErrorCode;
import com.expensetracker.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return AuthenticationResponse.builder().isAuthenticated(passwordEncoder.matches(request.getPassword(), user.getPassword())).build();
    }
}
