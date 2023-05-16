package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseResponseDTOMapperTest {

    private ExpenseResponseDTOMapper expenseResponseDTOMapper;

    @BeforeEach
    public void setUp() {
        expenseResponseDTOMapper = new ExpenseResponseDTOMapper();
    }

    @Test
    public void should_ReturnCorrectExpenseResponseDTO_When_ExpenseIsCorrect() {
        // given
        Expense expense = new Expense(
                "1",
                "ExpenseName",
                ExpenseCategory.RESTAURANT,
                BigDecimal.valueOf(99.00)
        );

        // when
        ExpenseResponseDTO expenseResponseDTO = expenseResponseDTOMapper.apply(expense);

        // then
        assertAll(
                () -> assertEquals(expense.getId(), expenseResponseDTO.id()),
                () -> assertEquals(expense.getExpenseName(), expenseResponseDTO.expenseName()),
                () -> assertEquals(expense.getExpenseCategory(), expenseResponseDTO.expenseCategory()),
                () -> assertEquals(expense.getExpenseAmount(), expenseResponseDTO.expenseAmount())
        );
    }
}