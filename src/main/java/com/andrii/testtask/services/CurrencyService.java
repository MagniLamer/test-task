package com.andrii.testtask.services;


import com.andrii.testtask.entity.CurrencyRequest;
import com.andrii.testtask.utils.CurrencyHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the work with 3d party system to get currency info
 */
@Service
public class CurrencyService {

    private final Logger LOG = Logger.getLogger(CurrencyService.class.getName());
    private final String HISTORY_RANGE_URL_BASE = "https://api.currencyapi.com/v3/range?";
    private final String LATEST_RANGE_URL_BASE = "https://api.currencyapi.com/v3/latest?";
    private final String CURRENCY_CODE_URL_BASE = "https://api.currencyapi.com/v3/currencies?";
    private final String API_KEY = "apikey=wLG96UGk3XaLmX6GSsDJFJVIb00Uk811ZN7xWsNC";

    private CurrencyRequestService currencyRequestService;
    private CurrencyResponseService currencyResponseService;
    private RestTemplate restTemplate = new RestTemplate();

    public CurrencyService(CurrencyRequestService currencyRequestService, CurrencyResponseService currencyResponseService) {
        this.currencyRequestService = currencyRequestService;
        this.currencyResponseService = currencyResponseService;
    }

    /**
     * Gets an exchange rates using the external API
     * @param baseCurrency
     * @param currencyName
     * @return
     */
    public ResponseEntity<String> getExchangeRates(String baseCurrency, String currencyName) {
        HashMap<String, String> parameters = new HashMap<>();
        if (baseCurrency != null) parameters.put("base_currency", baseCurrency);
        if (currencyName != null) parameters.put("currencies", currencyName);
        ResponseEntity<String> response = getResponseAndSave(LATEST_RANGE_URL_BASE, parameters);
        LOG.log(Level.INFO, "Exchange rates are received");
        return response;
    }


    /**
     * Gets an available currency codes of the currencies using the external API
     * @return
     */
    public ResponseEntity<String> getAvailableCurrencyCodes() {
        String request = CURRENCY_CODE_URL_BASE + API_KEY;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
        ResponseEntity<String> responseEntityWithFilteredJson =
                CurrencyHelper.getResponseEntityWithAvailableCodes(responseEntity);
        saveRequestAndResponseToDB(request, responseEntityWithFilteredJson);
        LOG.log(Level.INFO, "The available currency codes are received");
        return responseEntityWithFilteredJson;
    }


    /**
     * Gets an exchange rates history by criteria parameters using the external API
     * @param baseCurrency
     * @param currency
     * @param datetimeStart
     * @param datetimeEnd
     * @param accuracy
     * @return
     */
    public ResponseEntity<String> getExchangeRatesHistoryByCriteriaParameters(
            String baseCurrency, String currency, String datetimeStart, String datetimeEnd, String accuracy
    ) {
        HashMap<String, String> parameters = new HashMap<>();
        if (baseCurrency != null) parameters.put("base_currency", baseCurrency);
        if (currency != null) parameters.put("currencies", currency);
        if (accuracy != null) parameters.put("accuracy", accuracy);
        parameters.put("datetime_start", datetimeStart);
        parameters.put("datetime_end", datetimeEnd);

        ResponseEntity<String> response = getResponseAndSave(HISTORY_RANGE_URL_BASE, parameters);
        LOG.log(Level.INFO, "The exchange rate history is received");
        return response;
    }

    /**
     * Creates the request url using the base url and a set of parameters
     * @param baseURL the base url
     * @param parameters a set of parameters
     * @return  the request url
     */
    private String getRequestString(String baseURL, Map<String, String> parameters) {
        StringBuilder stringBuilder = new StringBuilder(baseURL);
        if(!parameters.isEmpty()){
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String parameterValue = parameter.getValue();
            if (parameterValue!=null) {
                stringBuilder.append(parameter.getKey());
                stringBuilder.append("=");
                stringBuilder.append(parameterValue);
                stringBuilder.append("&");
            }
        }}
        stringBuilder.append(API_KEY);
        LOG.log(Level.INFO, "The request string is built " );
        return stringBuilder.toString();
    }

    /**
     * Gets the ResponseEntity and saves the result in DB
     * @param url the targe url
     * @param parameters a set of parameters
     * @return ResponseEntity
     */
    private ResponseEntity<String> getResponseAndSave(String url, Map<String, String> parameters) {
        String request = getRequestString(url, parameters);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);
        saveRequestAndResponseToDB(request, responseEntity);
        LOG.log(Level.INFO, "The ResponseEntity  is received " );
        return responseEntity;
    }

    /**
     * Saves the request and the response to the embedded DB
     * @param request
     * @param responseEntity received ResponseEntity
     */
    private void saveRequestAndResponseToDB(String request, ResponseEntity<String> responseEntity) {
        CurrencyRequest currencyRequest = currencyRequestService.save(request);
        String response = responseEntity.getBody();
        currencyResponseService.save(currencyRequest, response);
        LOG.log(Level.INFO, "The request and response are saved in DB" );
    }


}
