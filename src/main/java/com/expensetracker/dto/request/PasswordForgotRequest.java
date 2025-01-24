package com.expensetracker.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordForgotRequest {
    private String email;
    private String password;
    private String confirmPassword;
}
