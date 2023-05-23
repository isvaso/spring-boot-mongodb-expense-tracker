package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Represents a DTO (Data Transfer Object) for a response containing expense information.
 * This class encapsulates the information of an expense, including its unique identifier, name, category, and amount.
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
public record ExpenseResponseDTO(

        @NotNull(message = "Can't be null")
        String id,

        @NotNull(message = "Can't be null")
        String expenseName,

        @NotNull(message = "Can't be null")
        ExpenseCategory expenseCategory,

        @NotNull(message = "Can't be null")
        BigDecimal expenseAmount
) {}
