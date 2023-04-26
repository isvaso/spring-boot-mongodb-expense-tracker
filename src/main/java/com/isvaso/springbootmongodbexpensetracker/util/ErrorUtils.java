package com.isvaso.springbootmongodbexpensetracker.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ErrorUtils {

    public static Optional<ResponseEntity<?>> getErrorResponse(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap));
        } else {
            return Optional.empty();
        }
    }
}
