package com.expensetracker.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OtpVerifyResponse {
    private boolean isValidOtp;
}
