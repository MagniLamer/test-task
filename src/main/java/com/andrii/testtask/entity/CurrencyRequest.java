package com.andrii.testtask.entity;

import javax.persistence.*;

@Entity
@Table(name = "currency_request")
public class CurrencyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String request;


    public CurrencyRequest() {
    }

    public CurrencyRequest(String request) {
        this.request = request;
    }

    public CurrencyRequest(long id, String request) {
        this.id = id;
        this.request = request;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
