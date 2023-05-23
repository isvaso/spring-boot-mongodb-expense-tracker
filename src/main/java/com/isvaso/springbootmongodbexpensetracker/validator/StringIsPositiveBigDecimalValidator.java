package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.util.BigDecimalUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator implementation for the {@link StringIsPositiveBigDecimal} constraint annotation.
 * Checks if a given string value is a valid positive BigDecimal.
 */
public class StringIsPositiveBigDecimalValidator
        implements ConstraintValidator<StringIsPositiveBigDecimal, String> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The {@link StringIsPositiveBigDecimal} constraint annotation instance.
     */
    @Override
    public void initialize(StringIsPositiveBigDecimal constraintAnnotation) {
    }

    /**
     * Checks if the provided string value is a valid positive BigDecimal.
     *
     * @param value                     The string value to be validated.
     * @param constraintValidatorContext The validation context.
     * @return {@code true} if the value is a valid positive BigDecimal or null, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || BigDecimalUtils.isPositiveBigDecimal(value);
    }
}
