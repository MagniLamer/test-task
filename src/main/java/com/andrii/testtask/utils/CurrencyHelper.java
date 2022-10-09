package com.andrii.testtask.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gets the required info from JSON
 */
public class CurrencyHelper {

    private static final Logger LOG = Logger.getLogger(CurrencyHelper.class.getName());

    /**
     * Gets the ResponseEntity with an available codes of the currency
     * @param responseEntity
     * @return ResponseEntity with an available codes of the currency
     */
    public static ResponseEntity<String> getResponseEntityWithAvailableCodes(ResponseEntity<String> responseEntity) {
        ObjectMapper mapper = new ObjectMapper();
        String root = responseEntity.getBody();
        String result = "";
        try {
            JsonNode rootNode = mapper.readTree(root);
            List<String> code = rootNode.findValuesAsText("code");
            result = "{\"codes\":" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(code) + "}";
            LOG.log(Level.INFO, "Available codes JSON is received" );
        } catch (JsonProcessingException e) {
            LOG.log(Level.SEVERE, "JSON wasn't read", e);
        }
        return new ResponseEntity<String>(result, responseEntity.getStatusCode());
    }

}
