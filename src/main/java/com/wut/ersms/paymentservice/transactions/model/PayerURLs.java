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
public class PayerURLs {

    @JsonProperty("success")
    private String success;
    @JsonProperty("error")
    private String error;
}
