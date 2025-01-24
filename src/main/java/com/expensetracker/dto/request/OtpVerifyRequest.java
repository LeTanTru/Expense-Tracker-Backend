package com.expensetracker.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class OtpVerifyRequest {
    private String email;
    private String otp;
}
