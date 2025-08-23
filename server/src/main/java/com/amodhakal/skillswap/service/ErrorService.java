package com.amodhakal.skillswap.service;

import org.springframework.http.ResponseEntity;


public interface ErrorService {
    public ResponseEntity<Object> handleErrorResponse(Exception exception);
}
