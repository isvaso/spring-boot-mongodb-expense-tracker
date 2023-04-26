package com.isvaso.springbootmongodbexpensetracker.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isvaso.springbootmongodbexpensetracker.deserializer.ExpenseAmountDeserializer;
import com.isvaso.springbootmongodbexpensetracker.deserializer.ExpenseCategoryDeserializer;
import com.isvaso.springbootmongodbexpensetracker.validator.StringIsBigDecimal;
import com.isvaso.springbootmongodbexpensetracker.validator.ExpenseCategorySubset;
import jakarta.validation.constraints.*;

public record ExpenseAddRequestDTO(

        @NotNull(message = "Can't be null")
        @NotBlank(message = "Can't be blank")
        @Size(min = 1, max = 255, message = "Length must be between {min} and {max}")
        String expenseName,

        @JsonDeserialize(using = ExpenseCategoryDeserializer.class)
        @NotNull(message = "Can't be null")
        @ExpenseCategorySubset
        String expenseCategory,

        @JsonDeserialize(using = ExpenseAmountDeserializer.class)
        @NotNull(message = "ExpenseAmount can't be null")
        @StringIsBigDecimal
        @DecimalMax(
                value = "999999.99",
                message = "ExpenseAmount can not be more than 999999.99")
        @DecimalMin(
                value = "0.00",
                message = "ExpenseAmount can not be less than 0.00")
        String expenseAmount

) {}
