package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.util.BigDecimalUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringIsPositiveBigDecimalValidator
        implements ConstraintValidator<StringIsPositiveBigDecimal, String> {

    @Override
    public void initialize(StringIsPositiveBigDecimal constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || BigDecimalUtils.isPositiveBigDecimal(value);
    }
}
