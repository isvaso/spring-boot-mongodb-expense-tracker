package com.isvaso.springbootmongodbexpensetracker.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExpenseCategorySubsetValidator.class)
public @interface ExpenseCategorySubset {

    String message() default "Must be any of ExpenseCategory";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
