package com.isvaso.springbootmongodbexpensetracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ErrorUtils.class);

    /**
     * Retrieves an error response from a BindingResult object.
     *
     * @param bindingResult The BindingResult object representing the binding results.
     * @return An Optional containing a ResponseEntity instance with an appropriate HTTP status code and error map,
     *         or an empty Optional if no errors were found.
     */
    public static Optional<ResponseEntity<?>> getErrorResponse(BindingResult bindingResult) {
        logger.debug("Checking field errors in BindingResult: {} for errors", bindingResult);

        if (Objects.nonNull(bindingResult) && bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            logger.info("Field errors found in BindingResult: {}", errorMap);
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap));
        } else {

            logger.info("No errors found in BindingResult");
            return Optional.empty();
        }
    }
}
