package com.isvaso.springbootmongodbexpensetracker.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StringIsPositiveBigDecimalValidator.class)
public @interface StringIsPositiveBigDecimal {

    String message() default "Must be a positive digit";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
