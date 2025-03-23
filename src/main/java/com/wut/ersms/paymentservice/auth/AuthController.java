package com.wut.ersms.paymentservice.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@Slf4j
@RestController
@RequestMapping("/tpay")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@PropertySource("classpath:secrets.properties")
public class AuthController {

    private final RestClient tpayRestClient;

    @Value("${tpay.client.id}")
    private String clientId;
    @Value("${tpay.client.secret}")
    private String clientSecret;

    @GetMapping("/auth")
    public TPayAuthResponse getOAuthToken() {
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
        return authResponse;
    }
}
