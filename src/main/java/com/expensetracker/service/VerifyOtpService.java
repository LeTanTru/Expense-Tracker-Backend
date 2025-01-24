package com.expensetracker.service;

import com.expensetracker.dto.request.OtpVerifyRequest;
import com.expensetracker.entity.User;
import com.expensetracker.exception.AppException;
import com.expensetracker.exception.ErrorCode;
import com.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerifyOtpService {
    private final UserRepository userRepository;

    public VerifyOtpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void sendOtp(OtpVerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        LocalDateTime now = LocalDateTime.now().plusMinutes(5);
        user.setOtp(request.getOtp());
        user.setOtpExpiry(now);
        userRepository.save(user);
    }

    public boolean isValidOtp(OtpVerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return request.getOtp().equals(user.getOtp());
    }

    public boolean isOtpExpired(OtpVerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return user.getOtpExpiry().isBefore(LocalDateTime.now());
    }

    public void verifyOtp(OtpVerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setVerified(true);
        userRepository.save(user);
    }

    public boolean isVerified(OtpVerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return user.isVerified();
    }
}
