package com.isvaso.springbootmongodbexpensetracker.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    public void should_ReturnExpectedStateOfExpense_When_ConstructorWasUsed() {
        // given
        String expenseId = "1";
        String expenseName = "ExpenseName";
        ExpenseCategory expenseCategory = ExpenseCategory.RESTAURANT;
        BigDecimal expenseAmount = BigDecimal.valueOf(24.00);

        Expense expense = new Expense(expenseId, expenseName, expenseCategory, expenseAmount);

        // when
        String retrievedId = expense.getId();
        String retrievedName = expense.getExpenseName();
        ExpenseCategory retrievedCategory = expense.getExpenseCategory();
        BigDecimal retrievedAmount = expense.getExpenseAmount();

        // then
        assertEquals(expenseId, retrievedId);
        assertEquals(expenseName, retrievedName);
        assertEquals(expenseCategory, retrievedCategory);
        assertEquals(expenseAmount, retrievedAmount);
    }

    @Test
    public void should_ReturnExpectedStateOfExpense_When_SettersWereUsed() {
        // given
        String expenseId = "1";
        String expenseName = "ExpenseName";
        ExpenseCategory expenseCategory = ExpenseCategory.RESTAURANT;
        BigDecimal expenseAmount = BigDecimal.valueOf(24.00);

        Expense expense = new Expense(expenseId, expenseName, expenseCategory, expenseAmount);

        String newExpenseId = "6";
        String newExpenseName = "NewExpenseName";
        ExpenseCategory newExpenseCategory = ExpenseCategory.UTILITIES;
        BigDecimal newExpenseAmount = BigDecimal.valueOf(21.12);

        expense.setId(newExpenseId);
        expense.setExpenseName(newExpenseName);
        expense.setExpenseCategory(newExpenseCategory);
        expense.setExpenseAmount(newExpenseAmount);

        // when
        String retrievedId = expense.getId();
        String retrievedName = expense.getExpenseName();
        ExpenseCategory retrievedCategory = expense.getExpenseCategory();
        BigDecimal retrievedAmount = expense.getExpenseAmount();

        // then
        assertEquals(newExpenseId, retrievedId);
        assertEquals(newExpenseName, retrievedName);
        assertEquals(newExpenseCategory, retrievedCategory);
        assertEquals(newExpenseAmount, retrievedAmount);
    }

}