package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Validator implementation for the {@link NullableExpenseCategorySubset} constraint annotation.
 * Checks if a given value is a valid ExpenseCategory or null.
 */
public class NullableExpenseCategorySubsetValidator
        implements ConstraintValidator<NullableExpenseCategorySubset, String> {

    /**
     * Initializes the validator.
     *
     * @param constraintAnnotation The {@link NullableExpenseCategorySubset} constraint annotation instance.
     */
    @Override
    public void initialize(NullableExpenseCategorySubset constraintAnnotation) {
    }

    /**
     * Checks if the provided value is a valid ExpenseCategory or null.
     *
     * @param value   The value to be validated.
     * @param context The validation context.
     * @return {@code true} if the value is a valid ExpenseCategory or null, {@code false} otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
                || Arrays.stream(ExpenseCategory.values())
                .map(ExpenseCategory::toString)
                .collect(Collectors.toList())
                .contains(value);
    }
}
