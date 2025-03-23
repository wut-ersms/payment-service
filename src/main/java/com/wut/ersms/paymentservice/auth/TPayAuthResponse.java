package com.wut.ersms.paymentservice.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wut.ersms.paymentservice.configuration.UTCLocalDateTimeDeserializer;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TPayAuthResponse {

    @JsonProperty("issued_at")
    @JsonDeserialize(using = UTCLocalDateTimeDeserializer.class)
    private LocalDateTime issuedAt;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("access_token")
    private String accessToken;

}
