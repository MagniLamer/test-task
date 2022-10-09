package com.andrii.testtask.utils;

import com.andrii.testtask.services.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;




class CurrencyHelperTest {


    @Test
    void givenResponseEntityWithJson_shouldReturnResponseEntityWithAvailableCodes() {
        ResponseEntity<String> responseEntity = ResponseEntity.accepted().body("{\"data\":{\"AED\":{\"symbol\":\"AED\",\"name\":\"United Arab Emirates Dirham\"," +
                "\"symbol_native\":\"د.إ\",\"decimal_digits\":2,\"rounding\":0,\"code\":\"AED\"," +
                "\"name_plural\":\"UAE dirhams\"},\"AFN\":{\"symbol\":\"Af\",\"name\":\"Afghan Afghani\"," +
                "\"symbol_native\":\"؋\",\"decimal_digits\":0,\"rounding\":0,\"code\":\"AFN\"," +
                "\"name_plural\":\"Afghan Afghanis\"}}}");

        ResponseEntity<String> gettedEntity = CurrencyHelper.getResponseEntityWithAvailableCodes(responseEntity);
        assertThat(gettedEntity.getBody()).contains("codes", "AFN", "AED");
        assertNotEquals(responseEntity, gettedEntity);
    }
}