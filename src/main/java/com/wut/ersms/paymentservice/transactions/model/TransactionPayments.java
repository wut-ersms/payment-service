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
public class TransactionPayments {

    @JsonProperty("status")
    private String status;
    @JsonProperty("method")
    private String method;
    @JsonProperty("amountPaid")
    private double amountPaid;
    @JsonProperty("date")
    private TransactionDate transactionDate;

}
