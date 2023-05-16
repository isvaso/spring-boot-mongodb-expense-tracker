package com.isvaso.springbootmongodbexpensetracker.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringIsBigDecimalValidatorTest {

    private StringIsBigDecimalValidator stringIsBigDecimalValidator;

    @BeforeEach
    public void setUp() {
        stringIsBigDecimalValidator = new StringIsBigDecimalValidator();
    }

    @Test
    public void should_ReturnTrue_When_ValueIsNull() {
        // given
        String value = null;

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnTrue_When_ValueIsPositiveBigDecimal() {
        // given
        String value = "10.00";

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnTrue_When_ValueIsNegativeBigDecimal() {
        // given
        String value = "-10.00";

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1.00", "999", "0.00", "99999999.999999", "-777.00"})
    public void should_ReturnTrue_When_MultipleValuesIsPositiveBigDecimal(String value) {
        // given

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnFalse_When_ValueIsNotBigDecimal() {
        // given
        String value = "ABS";

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @Test
    public void should_ReturnFalse_When_ValueIsEmpty() {
        // given
        String value = " ";

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"A A", "99o1", "1ABS", "ABS"})
    public void should_ReturnFalse_When_MultipleValuesIsNotBigDecimal(String value) {
        // given

        // when
        boolean actual = stringIsBigDecimalValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

}