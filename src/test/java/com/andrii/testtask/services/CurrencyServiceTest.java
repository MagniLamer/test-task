package com.andrii.testtask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest

class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void givenExchangeRatesRequest_whenBaseCurrencyAndCurrencyAreSet_shouldReturnStatusOK() throws IOException {
        ResponseEntity<String> actualExchangeRates = currencyService.getExchangeRates("USD", "EUR");
        assertThat(actualExchangeRates.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenExchangeRatesRequest_whenOnlyBaseCurrencyIsSet_shouldReturnStatusOK() throws IOException {
        ResponseEntity<String> actualExchangeRates = currencyService.getExchangeRates("EUR", null);
        assertThat(actualExchangeRates.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenExchangeRatesRequest_whenOnlyCurrencyIsSet_shouldReturnStatusOK() throws IOException {
        ResponseEntity<String> actualExchangeRates = currencyService.getExchangeRates(null, "EUR");
        assertThat(actualExchangeRates.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenExchangeRatesRequest_whenManyCurrenciesAreSet_shouldReturnStatusOK() throws IOException {
        ResponseEntity<String> actualExchangeRates = currencyService.getExchangeRates(null, "EUR,CAD");
        assertThat(actualExchangeRates.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void givenExchangeRatesRequest_whenManyCurrenciesAreSet_shouldContainCurrencyInfo() throws IOException {
        ResponseEntity<String> actualExchangeRates = currencyService.getExchangeRates(null, "EUR,CAD");
        ObjectMapper mapper = new ObjectMapper();
        String root = actualExchangeRates.getBody();
        List<String> code = null;
        try {
            JsonNode rootNode = mapper.readTree(root);
            code = rootNode.findValuesAsText("code");

        } catch (JsonProcessingException e) {

        }
        assertThat(code).containsOnly("EUR", "CAD");
    }


}