package com.expensetracker.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    boolean isAuthenticated;
}
