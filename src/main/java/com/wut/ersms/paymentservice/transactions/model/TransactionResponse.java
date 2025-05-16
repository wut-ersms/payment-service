package com.wut.ersms.paymentservice.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TransactionResponse {

    @JsonProperty("transactionPaymentUrl")
    private String transactionPaymentUrl;
    @JsonProperty("result")
    private String result;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("posId")
    private String posId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("status")
    private String status;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("description")
    private String description;
    @JsonProperty("date")
    private TransactionDate transactionDate;
    @JsonProperty("payer")
    private Payer payer;
    @JsonProperty("payments")
    private TransactionPayments transactionPayments;
}
