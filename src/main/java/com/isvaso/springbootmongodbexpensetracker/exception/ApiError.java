package com.isvaso.springbootmongodbexpensetracker.exception;

import java.time.LocalDate;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDate localDate
) {
}
