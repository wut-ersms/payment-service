package com.wut.ersms.paymentservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

//https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient

@Configuration
public class RestClientConfig {

    @Value("${tpay.api.url}")
    private String baseUrl;

    @Bean("tpayRestClient")
    public RestClient tpayRestClient() {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
//                .messageConverters(converters -> converters.add(new StringHttpMessageConverter()))
                .baseUrl(baseUrl)
//                .defaultUriVariables(Map.of("variable", "foo"))
//                .defaultHeader("My-Header", "Foo")
//                .defaultCookie("My-Cookie", "Bar")
                .build();
    }
}
