package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ExpenseResponseDTO(

        @NotNull(message = "Can't be null")
        String id,

        @NotNull(message = "Can't be null")
        String expenseName,

        @NotNull(message = "Can't be null")
        ExpenseCategory expenseCategory,

        @NotNull(message = "Can't be null")
        BigDecimal expenseAmount
) {
}
