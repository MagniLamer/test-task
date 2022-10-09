package com.andrii.testtask.services;

import com.andrii.testtask.entity.CurrencyRequest;
import com.andrii.testtask.repositories.CurrencyRequestRepository;
import org.springframework.stereotype.Service;


/**
 * Delegates the request saving to {@link CurrencyRequestRepository}
 */
@Service
public class CurrencyRequestService {

    private CurrencyRequestRepository repository;

    public CurrencyRequestService(CurrencyRequestRepository repository) {
        this.repository = repository;
    }

    public CurrencyRequest save(String request) {
        return repository.save(new CurrencyRequest(request));
    }
}
