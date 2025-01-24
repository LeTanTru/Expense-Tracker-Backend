package com.expensetracker.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequest {
    private String username;
    private String password;
}
