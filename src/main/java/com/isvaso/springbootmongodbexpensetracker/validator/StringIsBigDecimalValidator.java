package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.util.BigDecimalUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringIsBigDecimalValidator
        implements ConstraintValidator<StringIsBigDecimal, String> {

    @Override
    public void initialize(StringIsBigDecimal constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || BigDecimalUtils.isBigDecimal(value);
    }
}
