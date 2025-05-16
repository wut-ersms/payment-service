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
public class TransactionCallbacks {

    @JsonProperty("payerUrls")
    private PayerURLs payerURLs;
}
