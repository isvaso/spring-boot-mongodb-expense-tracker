package com.isvaso.springbootmongodbexpensetracker.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalUtilsTest {

    @Nested
    class IsBigDecimalTests {

        @ParameterizedTest(name = "value = {0}")
        @CsvSource(value = {"0", "-1", "0.5", "999999.0", "-999999"})
        void should_ReturnTrue_When_StringIsBigDecimal(String value) {
            // given
            String string = value;

            // when
            boolean isBigDecimal = BigDecimalUtils.isBigDecimal(string);

            // then
            assertTrue(isBigDecimal);
        }

        @ParameterizedTest(name = "value = {0}")
        @CsvSource(value = {"A", "1-1", "o.5", "-", "25.05.99"})
        void should_ReturnFalse_When_StringIsNotBigDecimal(String value) {
            // given
            String string = value;

            // when
            boolean isBigDecimal = BigDecimalUtils.isBigDecimal(string);

            // then
            assertFalse(isBigDecimal);
        }

        @Test
        void should_ReturnFalse_When_StringIsEmpty() {
            // given
            String string = "";

            // when
            boolean isBigDecimal = BigDecimalUtils.isBigDecimal(string);

            // then
            assertFalse(isBigDecimal);
        }

        @Test
        void should_ReturnFalse_When_StringIsSpace() {
            String string = "     ";

            boolean isBigDecimal = BigDecimalUtils.isBigDecimal(string);

            assertFalse(isBigDecimal);
        }

        @Test
        void should_ReturnFalse_When_StringIsNull() {
            String string = null;

            boolean isBigDecimal = BigDecimalUtils.isBigDecimal(string);

            assertFalse(isBigDecimal);
        }
    }

    @Nested
    class isPositiveBigDecimalTests {

        @ParameterizedTest(name = "value = {0}")
        @CsvSource(value = {"0", "1", "999.999"})
        void should_ReturnTrue_When_StringIsPositiveBigDecimal(String value) {
            String string = value;

            boolean isPositive = BigDecimalUtils.isPositiveBigDecimal(string);

            assertTrue(isPositive);
        }

        @ParameterizedTest(name = "value = {0}")
        @CsvSource(value = {"-0.1", "-1", "-999.999"})
        void should_ReturnFalse_When_StringIsNotPositiveBigDecimal(String value) {
            String string = value;

            boolean isPositive = BigDecimalUtils.isPositiveBigDecimal(string);

            assertFalse(isPositive);
        }

        @ParameterizedTest(name = "value = {0}")
        @CsvSource(value = {"-a", "1o1", "-999a"})
        void should_ReturnFalse_When_StringIsNotBigDecimal(String value) {
            String string = value;

            boolean isPositive = BigDecimalUtils.isPositiveBigDecimal(string);

            assertFalse(isPositive);
        }

        @Test
        void should_ReturnFalse_When_StringIsNull() {
            String string = null;

            boolean isPositive = BigDecimalUtils.isPositiveBigDecimal(string);

            assertFalse(isPositive);
        }
    }
}