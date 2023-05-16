package com.isvaso.springbootmongodbexpensetracker.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringIsPositiveBigDecimalValidatorTest {

    private StringIsPositiveBigDecimalValidator stringIsPositiveBigDecimalValidator;

    @BeforeEach
    public void setUp() {
        stringIsPositiveBigDecimalValidator = new StringIsPositiveBigDecimalValidator();
    }

    @Test
    public void should_ReturnTrue_When_ValueIsNull() {
        // given
        String value = null;

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnTrue_When_ValueIsPositiveBigDecimal() {
        // given
        String value = "10.00";

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"1.00", "999", "0.00", "99999999.999999"})
    public void should_ReturnTrue_When_MultipleValuesIsPositiveBigDecimal(String value) {
        // given

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnFalse_When_ValueIsNegativeBigDecimal() {
        // given
        String value = "-10.00";

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @Test
    public void should_ReturnFalse_When_ValueIsNotBigDecimal() {
        // given
        String value = "ABS";

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @Test
    public void should_ReturnFalse_When_ValueIsEmpty() {
        // given
        String value = " ";

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"-999", "-99999999.999999", "1ABS", "ABS"})
    public void should_ReturnFalse_When_MultipleValuesIsNotPositiveBigDecimal(String value) {
        // given

        // when
        boolean actual = stringIsPositiveBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

}