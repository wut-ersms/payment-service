package com.wut.ersms.paymentservice.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TransactionRequest {

    @JsonProperty("amount")
    private double amount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("payer")
    private Payer payer;
    @JsonProperty("callbacks")
    private TransactionCallbacks callbacks;
}
