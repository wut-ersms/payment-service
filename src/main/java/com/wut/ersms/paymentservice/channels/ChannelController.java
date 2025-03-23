package com.wut.ersms.paymentservice.channels;

import com.wut.ersms.paymentservice.auth.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ChannelController {

    private final RestClient tpayRestClient;
    private final AuthProvider authProvider;


    @GetMapping("/all")
    public String getAllChannels() {
        return tpayRestClient.get()
                .uri("/transactions/channels")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + authProvider.getAccessToken())
                .retrieve()
                .body(String.class);
    }
}
