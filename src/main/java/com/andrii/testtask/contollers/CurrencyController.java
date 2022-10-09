package com.andrii.testtask.contollers;

import com.andrii.testtask.services.CurrencyService;
import com.andrii.testtask.utils.CurrencyHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Makes mapping REST requests
 */
@RestController
public class CurrencyController {

    private CurrencyService currensiService;

    public CurrencyController(CurrencyService currensiService) {
        this.currensiService = currensiService;
    }

    /**
     * Gets an exchange rates using a base currency and currency.
     *
     * @param baseCurrency The base currency to which all results are behaving relative to
     *                     By default all values are based on USD
     * @param currency     A list of comma seperated currency codes which you want to get (EUR,USD,CAD)
     *                     By default all available currencies will be shown
     * @return the ResponseEntity value
     */
    @GetMapping("/currencies/exchange-rates/")
    public ResponseEntity<String> getExchangeRates(
            @RequestParam(name = "base-currency", required = false) String baseCurrency,
            @RequestParam(name = "currency", required = false) String currency) {
        return currensiService.getExchangeRates(baseCurrency, currency);
    }


    /**
     * Gets an available currency codes of the currencies
     *
     * @return the ResponseEntity value with available currency codes of the all currencies
     */
    @GetMapping("/currencies/codes/")
    public ResponseEntity<String> getAvailableCurrencyCodes() {
        return currensiService.getAvailableCurrencyCodes();
    }

    /**
     * Gets an exchange rates history by criteria parameters
     *
     * @param baseCurrency  The base currency to which all results are behaving relative to
     *                      By default all values are based on USD.
     * @param currency      A list of comma seperated currency codes which you want to get (EUR,USD,CAD)
     *                      By default all available currencies will be shown
     * @param accuracy      The accuracy you want to receive.
     *                      Possible Values: day, hour, quarter_hour, minute
     *                      Default: day
     *                      For valid time ranges see below
     * @param datetimeStart Datetime for the start of your requested range (format: 2021-12-31T23:59:59Z / ISO8601 Datetime).
     *                      Required.
     * @param datetimeEnd   Datetime for the end of your requested range (format: 2021-12-31T23:59:59Z / ISO8601 Datetime).
     *                      Required.
     * @return ResponseEntity value
     */
    @GetMapping("/currencies/rates/")
    public ResponseEntity<String> getExchangeRatesHistoryByCriteriaParameters(
            @RequestParam(name = "base-currency", required = false) String baseCurrency,
            @RequestParam(name = "currency", required = false) String currency,
            @RequestParam(name = "accuracy", required = false) String accuracy,
            @RequestParam(name = "datetime-start") String datetimeStart,
            @RequestParam(name = "datetime-end") String datetimeEnd
    ) {
        return currensiService
                .getExchangeRatesHistoryByCriteriaParameters(baseCurrency, currency, datetimeStart, datetimeEnd, accuracy);
    }


}
