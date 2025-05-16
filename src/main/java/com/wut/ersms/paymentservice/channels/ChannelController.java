package com.wut.ersms.paymentservice.channels;

import com.wut.ersms.paymentservice.auth.AuthProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Channel", description = "Controller to get all transaction methods available")
public class ChannelController {

    private final RestClient tpayRestClient;
    private final AuthProvider authProvider;

    @Operation(summary = "Fetch all channel types", description = "Some description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/all")
    public ResponseEntity<String> getAllChannels() {
        return tpayRestClient.get()
                .uri("/transactions/channels")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer " + authProvider.getAccessToken())
                .retrieve()
                .toEntity(String.class);
    }
}
