package com.andrii.testtask.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Handles HttpClientErrorException in all services
 */
@ControllerAdvice
public class ErrorHandler {

    /**
     * Catches  HttpClientErrorException and return the bad request ResponseEntity
     * @param ex
     * @return the bad request ResponseEntity
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleException(HttpClientErrorException ex){
        return ResponseEntity.badRequest().body(ex.getResponseBodyAsString());
    }
}
