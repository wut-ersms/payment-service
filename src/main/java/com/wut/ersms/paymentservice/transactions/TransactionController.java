package com.wut.ersms.paymentservice.transactions;

import com.wut.ersms.paymentservice.auth.AuthProvider;
import com.wut.ersms.paymentservice.transactions.model.Payer;
import com.wut.ersms.paymentservice.transactions.model.PayerURLs;
import com.wut.ersms.paymentservice.transactions.model.TransactionCallbacks;
import com.wut.ersms.paymentservice.transactions.model.TransactionRequest;
import com.wut.ersms.paymentservice.transactions.model.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Transaction", description = "Controller to manage transactions")
public class TransactionController {

    @Value("${server.ip}")
    private String serverAddress;

    private final RestClient tpayRestClient;
    private final AuthProvider authProvider;

    @Operation(summary = "Create new transaction", description = "New transaction is created with passed params, then saved to database and redirected to payment url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/new")
    public ResponseEntity<Void> newTransaction(@RequestParam double amount, @RequestParam String description, @RequestParam String email,
                                               @RequestParam String name, @RequestParam String successPage, @RequestParam String errorPage) {
        var transactionRequest = TransactionRequest.builder()
                .amount(amount)
                .description(description)
                .payer(Payer.builder()
                        .email(email)
                        .name(name)
                        .build())
                .callbacks(TransactionCallbacks.builder()
                        .payerURLs(PayerURLs.builder()
                                .success(successPage)
                                .error(errorPage)
                                .build())
                        .build())
                .build();

        var transactionResponse = tpayRestClient.post()
                .uri("/transactions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authProvider.getAccessToken())
                .body(transactionRequest)
                .retrieve()
                .body(TransactionResponse.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", transactionResponse.getTransactionPaymentUrl());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);

        //TODO save response to db
    }
}
