package com.expensetracker.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UpdateUserRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String firstName;
    private String lastName;
    private LocalDate dob;

    @Pattern(regexp = "\\d{10}", message = "PHONE_NUMBER_INVALID")
    private String phoneNumber;
}
