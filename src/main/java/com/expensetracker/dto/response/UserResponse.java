package com.expensetracker.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String phoneNumber;
    private String otp;
    private LocalDateTime otpExpiry;
    private boolean isVerified;
}
