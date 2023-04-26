package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NullableExpenseCategorySubsetValidator
        implements ConstraintValidator<NullableExpenseCategorySubset, String> {

    @Override
    public void initialize(NullableExpenseCategorySubset constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
                || Arrays.stream(ExpenseCategory.values())
                .map(ExpenseCategory::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
