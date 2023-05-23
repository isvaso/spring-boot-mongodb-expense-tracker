package com.isvaso.springbootmongodbexpensetracker.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isvaso.springbootmongodbexpensetracker.deserializer.ExpenseAmountDeserializer;
import com.isvaso.springbootmongodbexpensetracker.deserializer.ExpenseCategoryDeserializer;
import com.isvaso.springbootmongodbexpensetracker.validator.*;
import jakarta.validation.constraints.*;

/**
 * Represents a DTO (Data Transfer Object) for updating an expense.
 * This class encapsulates the information required to update an expense.
 * It includes its unique identifier, name, category, and amount of the expense.
 *
 * @param id              The unique identifier of the expense.
 *                        @apiNote This field must not be null.
 * @param expenseName     The name of the expense.
 *                        @apiNote This field must not be null.
 * @param expenseCategory The category of the expense.
 *                        @apiNote This field must not be null and must be a valid expense category.
 * @param expenseAmount   The amount of the expense.
 *                        @apiNote This field must not be null, must be a valid decimal number, and must be within the range of 0.00 to 999999.99.
 */
public record ExpenseUpdateRequestDTO(
        @NotEmpty(message = "Can't be empty")
        @NotNull(message = "Can't be null")
        String id,

        @NotBlank(message = "Can't be blank")
        @Size(min = 1, max = 255, message = "Length must be between {min} and {max}")
        String expenseName,

        @JsonDeserialize(using = ExpenseCategoryDeserializer.class)
        @NullableExpenseCategorySubset
        String expenseCategory,

        @JsonDeserialize(using = ExpenseAmountDeserializer.class)
        @StringIsBigDecimal
        @DecimalMax(
                value = "999999.99",
                message = "ExpenseAmount can not be more than 999999.99")
        @DecimalMin(
                value = "0.00",
                message = "ExpenseAmount can not be less than 0.00")
        String expenseAmount
) {}
