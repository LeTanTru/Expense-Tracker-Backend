package com.expensetracker.controller;

import com.expensetracker.dto.request.OtpVerifyRequest;
import com.expensetracker.dto.response.ApiResponse;
import com.expensetracker.dto.response.OtpVerifyResponse;
import com.expensetracker.service.EmailSenderService;
import com.expensetracker.service.VerifyOtpService;
import com.expensetracker.utils.OTPGeneratorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyOtpController {

    private final VerifyOtpService verifyOtpService;
    private final EmailSenderService emailSenderService;

    public VerifyOtpController(VerifyOtpService verifyOtpService, EmailSenderService emailSenderService) {
        this.verifyOtpService = verifyOtpService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-otp")
    public ApiResponse<String> sendOtp(@RequestBody OtpVerifyRequest request) {
        String otp = OTPGeneratorUtil.generateOTP();
        request.setOtp(otp);
        verifyOtpService.sendOtp(request);
        emailSenderService.sendOtpEmail(request.getEmail(), otp);

//        boolean isVerified = verifyOtpService.isVerified(request);
//
//        if (isVerified) {
//            return ApiResponse.<String>builder()
//                    .code(HttpStatus.OK.value())
//                    .message("Email verified !")
//                    .build();
//        }

        boolean isOtpExpired = verifyOtpService.isOtpExpired(request);
        if (!isOtpExpired) {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.OK.value())
                    .message("Previous Otp still not expired !")
                    .build();
        }

        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("Otp sent !")
                .build();
    }

    @PostMapping("/verify-otp")
    public ApiResponse<OtpVerifyResponse> verifyOtp(@RequestBody OtpVerifyRequest request) {

        boolean isOtpExpired = verifyOtpService.isOtpExpired(request);
        if (isOtpExpired) {
            return ApiResponse.<OtpVerifyResponse>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Otp is expired !")
                    .build();
        }

        boolean isValidOtp = verifyOtpService.isValidOtp(request);
        if (!isValidOtp) {
            return ApiResponse.<OtpVerifyResponse>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Invalid Otp !")
                    .build();
        }

        verifyOtpService.verifyOtp(request);

        return ApiResponse.<OtpVerifyResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Otp verified !")
                .build();
    }


}
