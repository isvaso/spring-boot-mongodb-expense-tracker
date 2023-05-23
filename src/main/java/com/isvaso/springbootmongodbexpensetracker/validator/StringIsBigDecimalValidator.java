package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.util.BigDecimalUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator implementation for the {@link StringIsBigDecimal} constraint annotation.
 * Checks if a given string value is a valid BigDecimal.
 */
public class StringIsBigDecimalValidator
        implements ConstraintValidator<StringIsBigDecimal, String> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The {@link StringIsBigDecimal} constraint annotation instance.
     */
    @Override
    public void initialize(StringIsBigDecimal constraintAnnotation) {
    }

    /**
     * Checks if the provided string value is a valid BigDecimal.
     *
     * @param value                     The string value to be validated.
     * @param constraintValidatorContext The validation context.
     * @return {@code true} if the value is a valid BigDecimal or null, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || BigDecimalUtils.isBigDecimal(value);
    }
}
