package com.andrii.testtask.services;

import com.andrii.testtask.entity.CurrencyRequest;
import com.andrii.testtask.entity.CurrencyResponse;
import com.andrii.testtask.repositories.CurrencyRequestRepository;
import com.andrii.testtask.repositories.CurrencyResponseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Delegates the response saving to {@link CurrencyResponseRepository}
 */
@Service
public class CurrencyResponseService {

    private CurrencyResponseRepository currencyResponseRepository;

    public CurrencyResponseService(CurrencyResponseRepository currencyResponseRepository) {
        this.currencyResponseRepository = currencyResponseRepository;
    }

    public void save(CurrencyRequest currencyRequest, String response) {
        currencyResponseRepository.save(new CurrencyResponse(response, currencyRequest));
    }
}
