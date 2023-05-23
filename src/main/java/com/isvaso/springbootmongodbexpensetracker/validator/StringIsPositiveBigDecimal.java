package com.isvaso.springbootmongodbexpensetracker.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom constraint annotation used to validate if a field's value is a positive BigDecimal.
 * The annotated field should be of type String and must be a valid BigDecimal value and grater than or equal to zero.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StringIsPositiveBigDecimalValidator.class)
public @interface StringIsPositiveBigDecimal {

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
