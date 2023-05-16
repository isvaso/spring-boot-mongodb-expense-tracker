package com.isvaso.springbootmongodbexpensetracker.util;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static boolean isBigDecimal(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isPositiveBigDecimal(String str) {
        return isBigDecimal(str) && new BigDecimal(str).compareTo(BigDecimal.ZERO) >= 0;
    }
}
