package com.isvaso.springbootmongodbexpensetracker.validator;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseCategorySubsetValidatorTest {

    private ExpenseCategorySubsetValidator expenseCategorySubsetValidator;
    private String[] expenseCategories;
    private Random random;

    @BeforeEach
    public void getAllExpenseCategories() {
        expenseCategorySubsetValidator = new ExpenseCategorySubsetValidator();

        List<ExpenseCategory> expenseCategoriesList = Arrays.asList(ExpenseCategory.values());
        expenseCategories = expenseCategoriesList.stream()
                .map(ExpenseCategory::name)
                .toArray(String[]::new);

        random = new Random();
    }

    @Test
    public void should_ReturnTrue_When_StringIsInExpenseCategorySubset() {
        // given
        String value = "MISC";

        // when
        boolean actual = expenseCategorySubsetValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnTrue_When_StringIsInRandomExpenseCategorySubset() {
        // given
        String value = expenseCategories[random.nextInt(0, expenseCategories.length)];

        // when
        boolean actual = expenseCategorySubsetValidator.isValid(value, null);

        // then
        assertTrue(actual);
    }

    @Test
    public void should_ReturnFalse_When_StringIsNull() {
        // given
        String value = null;

        // when
        boolean actual = expenseCategorySubsetValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @Test
    public void should_ReturnFalse_When_StringIsEmpty() {
        // given
        String value = " ";

        // when
        boolean actual = expenseCategorySubsetValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

    @Test
    public void should_ReturnTrue_When_StringIsNotInExpenseCategorySubset() {
        // given
        String value = "Hello";

        // when
        boolean actual = expenseCategorySubsetValidator.isValid(value, null);

        // then
        assertFalse(actual);
    }

}