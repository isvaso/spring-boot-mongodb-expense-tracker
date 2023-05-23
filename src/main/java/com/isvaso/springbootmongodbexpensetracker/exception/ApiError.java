package com.isvaso.springbootmongodbexpensetracker.exception;

import java.time.LocalDate;

/**
 * Represents an API error response.
 * This class encapsulates information about the error occurred during API request processing.
 *
 * @param path The path of the API request that resulted in the error.
 * @param message The error message.
 * @param statusCode The HTTP status code associated with the error.
 * @param localDate The local date when the error occurred.
 */
public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDate localDate
) {}
