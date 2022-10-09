package com.andrii.testtask.contollers;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.tomcat.util.json.JSONParser;
import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.Import;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;


class ApiTest {

    @Test
    public void givenExchangeRatesRequest_shouldReturnStatusOK() throws IOException {
        String requestURL = "http://localhost:8080/currencies/exchange-rates/" +
                "?base_currency=USD" +
                "&currencies=EUR";
        HttpUriRequest request = new HttpGet( requestURL);
        CloseableHttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
                assertThat(httpResponse.getCode()).isEqualTo(200);

          }



}