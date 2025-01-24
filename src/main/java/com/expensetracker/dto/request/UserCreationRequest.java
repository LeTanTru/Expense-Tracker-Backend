package com.expensetracker.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    private String username;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "EMAIL_INVALID"
    )
    private String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String firstName;
    private String lastName;
    private LocalDate dob;

    @Pattern(regexp = "\\d{10}", message = "PHONE_NUMBER_INVALID")
    private String phoneNumber;

    private String otp;
    private LocalDateTime otpExpiry;
    private boolean isVerified;
}
