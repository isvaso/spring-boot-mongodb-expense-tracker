package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Validator implementation for the {@link ExpenseCategorySubset} constraint annotation.
 * Checks if a given value is a valid ExpenseCategory.
 */
public class ExpenseCategorySubsetValidator
        implements ConstraintValidator<ExpenseCategorySubset, String> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The {@link ExpenseCategorySubset} constraint annotation instance.
     */
    @Override
    public void initialize(ExpenseCategorySubset constraintAnnotation) {
    }

    /**
     * Checks if the provided value is a valid ExpenseCategory.
     *
     * @param value   The value to be validated.
     * @param context The validation context.
     * @return {@code true} if the value is a valid ExpenseCategory, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(ExpenseCategory.values())
                .map(ExpenseCategory::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
