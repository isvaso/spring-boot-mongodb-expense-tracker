package com.isvaso.springbootmongodbexpensetracker.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom constraint annotation used to validate if a field's value is a BigDecimal.
 * The annotated field should be of type String and must be a valid BigDecimal value.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StringIsBigDecimalValidator.class)
public @interface StringIsBigDecimal {

    /**
     * The error message template to be used when the validation fails.
     *
     * @return The error message template.
     */
    String message() default "Must be a positive digit";

    /**
     * Groups targeted for validation.
     *
     * @return The targeted groups.
     */
    Class<?>[] groups() default {};

    /**
     * Payload type to associate with the constraint.
     *
     * @return The payload type.
     */
    Class<? extends Payload>[] payload() default {};
}
