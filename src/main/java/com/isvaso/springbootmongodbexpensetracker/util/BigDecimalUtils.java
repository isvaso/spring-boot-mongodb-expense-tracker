package com.isvaso.springbootmongodbexpensetracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Provides utility methods for checking if a String value can be parsed as BigDecimal.
 * Contains methods for checking if a String can be converted to BigDecimal and if the resulting BigDecimal is positive.
 */
public class BigDecimalUtils {

    private static final Logger logger = LoggerFactory.getLogger(BigDecimalUtils.class);

    /**
     * Checks if the provided String value can be parsed as a BigDecimal.
     *
     * @param str The String value to check.
     * @return {@code true} if the String value can be converted to BigDecimal, {@code false} otherwise.
     */
    public static boolean isBigDecimal(String str) {
        logger.debug("Trying to validate String {} is BigDecimal", str);

        try {
            new BigDecimal(str);

            logger.info("Validation String is BigDecimal was successful");
            return true;
        } catch (NumberFormatException | NullPointerException e) {

            logger.debug("Error occurred while parsing BigDecimal: {}", e.getMessage());
            logger.info("Validation String is BigDecimal failed");
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
        logger.debug("Trying to validate String {} is positive BigDecimal", str);

        if (!isBigDecimal(str)) {
            return false;
        }

        BigDecimal value = new BigDecimal(str);

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            logger.debug("Negative BigDecimal value provided: {}", str);
            logger.info("Validation String is positive BigDecimal failed");
            return false;
        }

        logger.info("Validation String is positive BigDecimal was successful");
        return true;
    }
}
