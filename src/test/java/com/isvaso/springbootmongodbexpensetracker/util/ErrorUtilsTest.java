package com.isvaso.springbootmongodbexpensetracker.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErrorUtilsTest {

    @Mock
    private BindingResult bindingResultMock;

    @Test
    void should_ReturnEmptyOptional_When_BindingResultIsNull() {
        // given
        BindingResult bindingResult = null;

        // when
        Optional<ResponseEntity<?>> optional = ErrorUtils.getErrorResponse(bindingResult);
        boolean isEmpty = optional.isEmpty();

        // then
        assertTrue(isEmpty);
    }

    @Test
    void should_ReturnEmptyOptional_When_BindingResultIsEmptyAndNonNull() {
        // given
        when(bindingResultMock.hasErrors()).thenReturn(false);

        // when
        Optional<ResponseEntity<?>> optional = ErrorUtils.getErrorResponse(bindingResultMock);
        boolean isEmpty = Objects.nonNull(bindingResultMock) && optional.isEmpty();

        // then
        assertTrue(isEmpty);
    }

    @Test
    void should_ReturnNotEmptyOptional_When_BindingResultIsNotEmpty() {
        // given
        List<FieldError> fieldErrorList = Arrays.asList(
                new FieldError(
                        "ExampleFieldError",
                        "ExampleField",
                        "Example message"));
        when(bindingResultMock.hasErrors()).thenReturn(true);
        when(bindingResultMock.getFieldErrors()).thenReturn(fieldErrorList);

        // when
        Optional<ResponseEntity<?>> optional = ErrorUtils.getErrorResponse(bindingResultMock);
        boolean isEmpty = optional.isEmpty();

        // then
        assertFalse(isEmpty);
    }
}