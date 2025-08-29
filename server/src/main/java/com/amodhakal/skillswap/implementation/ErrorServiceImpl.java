package com.amodhakal.skillswap.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.amodhakal.skillswap.dto.ErrorDto;
import com.amodhakal.skillswap.service.ErrorService;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public ResponseEntity<Object> handleErrorResponse(Exception exception) {
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }

}
