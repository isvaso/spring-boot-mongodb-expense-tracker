package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ExpenseCategorySubsetValidator
        implements ConstraintValidator<ExpenseCategorySubset, String> {

    @Override
    public void initialize(ExpenseCategorySubset constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(ExpenseCategory.values())
                .map(ExpenseCategory::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
