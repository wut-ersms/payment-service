package com.wut.ersms.paymentservice.auth;

import com.hazelcast.map.IMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@PropertySource("classpath:secrets.properties")
public class AuthProvider {

    private static final String TOKEN_KEY = "token";

    private final RestClient tpayRestClient;
    private final IMap<String, TPayAuthResponse> authMap;

    @Value("${tpay.client.id}")
    private String clientId;
    @Value("${tpay.client.secret}")
    private String clientSecret;

    public String getAccessToken() {
        return Optional.ofNullable(authMap.get(TOKEN_KEY))
                .orElseGet(this::getOAuthToken)
                .getAccessToken();
    }

    private void put(TPayAuthResponse tPayAuthResponse) {
        authMap.putIfAbsent(TOKEN_KEY, tPayAuthResponse, tPayAuthResponse.getExpiresIn(), TimeUnit.SECONDS);
    }

    private TPayAuthResponse getOAuthToken() {
        TPayAuthResponse authResponse = tpayRestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/auth")
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .build())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        ((request, response) -> {
                            log.error("Client error with code {} and headers: {} ", response.getStatusCode(), response.getHeaders());
                            throw new RuntimeException("Client error with code " + response.getStatusCode());
                        }))
                .onStatus(HttpStatusCode::isError,
                        ((request, response) -> {
                            log.error("Error with code {} and headers: {} ", response.getStatusCode(), response.getHeaders());
                            throw new RuntimeException("Error with code " + response.getStatusCode());
                        }))
                .body(TPayAuthResponse.class);
        log.info("OAuth token response: {}", authResponse);
        put(authResponse);
        return authResponse;
    }
}
