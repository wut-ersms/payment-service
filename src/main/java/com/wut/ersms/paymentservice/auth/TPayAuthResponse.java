package com.wut.ersms.paymentservice.auth;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TPayAuthResponse {

    private LocalDateTime issuedAt;
    private String scope;
    private String tokenType;
    private Long expiresIn;
    private String clientId;
    private String accessToken;

}
