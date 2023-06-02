package com.isvaso.springbootmongodbexpensetracker.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Global exception handler for the application.
 * This class provides exception handling for various types of exceptions that may occur during API request processing.
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    /**
     * Handles the MethodArgumentNotValidException and returns a map of field errors.
     *
     * @param e The MethodArgumentNotValidException instance.
     * @return A map containing field names as keys and corresponding error messages as values.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        logger.error("MethodArgumentNotValidException occurred with errorMap: {}", errorMap);
        return errorMap;
    }

    /**
     * Handles the JsonProcessingException and returns a ResponseEntity with API errors.
     *
     * @param e       The JsonProcessingException instance.
     * @param request The HttpServletRequest object representing the current request.
     * @return A ResponseEntity containing an ApiError instance and HTTP status code.
     */
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiError> handleJsonProcessingException(JsonProcessingException e,
                                                                  HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now()
        );

        logger.error("JsonProcessingException occurred with ApiError: {}", apiError);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the general Exception and RuntimeException and returns an ApiError response entity.
     *
     * @param e       The Exception instance.
     * @param request The HttpServletRequest object representing the current request.
     * @return A ResponseEntity containing an ApiError instance and HTTP status code.
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ApiError> handleException(Exception e,
                                                    HttpServletRequest request) {

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDate.now()
        );

        logger.error("{} occurred with ApiError: {}", e.getClass().getSimpleName(), apiError);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the NoSuchElementException and returns an ApiError response entity.
     *
     * @param e       The NoSuchElementException instance.
     * @param request The HttpServletRequest object representing the current request.
     * @return A ResponseEntity containing an ApiError instance and HTTP status code.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException e,
                                                                 HttpServletRequest request) {

        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now()
        );

        logger.error("NoSuchElementException occurred with ApiError: {}", apiError);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
