package com.isvaso.springbootmongodbexpensetracker.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Provides utility methods for working with application errors.
 * This class contains methods for handling and processing errors that occur in the application.
 */
public class ErrorUtils {

    /**
     * Retrieves an error response from a BindingResult object.
     *
     * @param bindingResult The BindingResult object representing the binding results.
     * @return An Optional containing a ResponseEntity instance with an appropriate HTTP status code and error map,
     *         or an empty Optional if no errors were found.
     */
    public static Optional<ResponseEntity<?>> getErrorResponse(BindingResult bindingResult) {
        if (Objects.nonNull(bindingResult) && bindingResult.hasErrors()) {
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
