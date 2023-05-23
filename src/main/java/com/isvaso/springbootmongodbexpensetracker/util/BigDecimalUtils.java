package com.isvaso.springbootmongodbexpensetracker.util;

import java.math.BigDecimal;

/**
 * Provides utility methods for checking if a String value can be parsed as BigDecimal.
 * Contains methods for checking if a String can be converted to BigDecimal and if the resulting BigDecimal is positive.
 */
public class BigDecimalUtils {

    /**
     * Checks if the provided String value can be parsed as a BigDecimal.
     *
     * @param str The String value to check.
     * @return {@code true} if the String value can be converted to BigDecimal, {@code false} otherwise.
     */
    public static boolean isBigDecimal(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Checks if the provided String value can be parsed as a BigDecimal and if the resulting BigDecimal is positive.
     *
     * @param str The String value to check.
     * @return {@code true} if the String value can be converted to BigDecimal and the resulting BigDecimal is greater than or equal to zero,
     *         {@code false} otherwise.
     */
    public static boolean isPositiveBigDecimal(String str) {
        return isBigDecimal(str) && new BigDecimal(str).compareTo(BigDecimal.ZERO) >= 0;
    }
}
