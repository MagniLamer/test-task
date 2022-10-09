package com.andrii.testtask.entity;

import javax.persistence.*;

@Entity
@Table(name="currency_response")
public class CurrencyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String response;

    @OneToOne
    private CurrencyRequest currencyRequest;

    public CurrencyResponse() {
    }

    public CurrencyResponse(long id, String response, CurrencyRequest currencyRequest) {
        this.id = id;
        this.response = response;
        this.currencyRequest = currencyRequest;
    }

    public CurrencyResponse(String response, CurrencyRequest currencyRequest) {
        this.response = response;
        this.currencyRequest = currencyRequest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public CurrencyRequest getCurrencyRequest() {
        return currencyRequest;
    }

    public void setCurrencyRequest(CurrencyRequest currencyRequest) {
        this.currencyRequest = currencyRequest;
    }
}
