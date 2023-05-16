package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseResponseDTOTest {

    private Validator validator;

    private ExpenseResponseDTO expenseResponseDTO;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void should_NotReturnFieldErrors_When_FieldsAreFilled() {
        // given
        String expenseId = "1";
        String expenseName = "ExpenseName";
        ExpenseCategory expenseCategory = ExpenseCategory.GROCERIES;
        BigDecimal expenseAmount = BigDecimal.valueOf(12.00);

        // when
        expenseResponseDTO = new ExpenseResponseDTO(expenseId, expenseName, expenseCategory, expenseAmount);
        Set<ConstraintViolation<ExpenseResponseDTO>> validationErrors = validator.validate(expenseResponseDTO);

        // then
        assertTrue(validationErrors.isEmpty());
    }

    @Test
    public void should_ReturnIdFieldCantBeNullError_When_IdFieldIsNull() {
        // given
        String expenseId = null;
        String expenseName = "ExpenseName";
        ExpenseCategory expenseCategory = ExpenseCategory.GROCERIES;
        BigDecimal expenseAmount = BigDecimal.valueOf(12.00);

        String expectedErrorMessage = "Can't be null";
        String expectedPropertyPath = "id";

        // when
        expenseResponseDTO = new ExpenseResponseDTO(expenseId, expenseName, expenseCategory, expenseAmount);
        Set<ConstraintViolation<ExpenseResponseDTO>> validationErrors = validator.validate(expenseResponseDTO);

        // then
        assertAll(
                () -> assertEquals(1, validationErrors.size()),
                () -> assertEquals(expectedErrorMessage, validationErrors.iterator().next().getMessage()),
                () -> assertEquals(expectedPropertyPath, validationErrors.iterator().next().getPropertyPath().toString())
        );
    }

    @Test
    public void should_ReturnExpenseNameFieldCantBeNullError_When_ExpenseNameFieldIsNull() {
        // given
        String expenseId = "1";
        String expenseName = null;
        ExpenseCategory expenseCategory = ExpenseCategory.GROCERIES;
        BigDecimal expenseAmount = BigDecimal.valueOf(12.00);

        String expectedErrorMessage = "Can't be null";
        String expectedPropertyPath = "expenseName";

        // when
        expenseResponseDTO = new ExpenseResponseDTO(expenseId, expenseName, expenseCategory, expenseAmount);
        Set<ConstraintViolation<ExpenseResponseDTO>> validationErrors = validator.validate(expenseResponseDTO);

        // then
        assertAll(
                () -> assertEquals(1, validationErrors.size()),
                () -> assertEquals(expectedErrorMessage, validationErrors.iterator().next().getMessage()),
                () -> assertEquals(expectedPropertyPath, validationErrors.iterator().next().getPropertyPath().toString())
        );
    }

    @Test
    public void should_ReturnExpenseCategoryFieldCantBeNullError_When_ExpenseCategoryFieldIsNull() {
        // given
        String expenseId = "1";
        String expenseName = "ExpenseName";
        ExpenseCategory expenseCategory = null;
        BigDecimal expenseAmount = BigDecimal.valueOf(12.00);

        String expectedErrorMessage = "Can't be null";
        String expectedPropertyPath = "expenseCategory";

        // when
        expenseResponseDTO = new ExpenseResponseDTO(expenseId, expenseName, expenseCategory, expenseAmount);
        Set<ConstraintViolation<ExpenseResponseDTO>> validationErrors = validator.validate(expenseResponseDTO);

        // then
        assertAll(
                () -> assertEquals(1, validationErrors.size()),
                () -> assertEquals(expectedErrorMessage, validationErrors.iterator().next().getMessage()),
                () -> assertEquals(expectedPropertyPath, validationErrors.iterator().next().getPropertyPath().toString())
        );
    }

    @Test
    public void should_ReturnExpenseAmountFieldCantBeNullError_When_ExpenseAmountFieldIsNull() {
        // given
        String expenseId = "1";
        String expenseName = "ExpenseName";
        ExpenseCategory expenseCategory = ExpenseCategory.GROCERIES;
        BigDecimal expenseAmount = null;

        String expectedErrorMessage = "Can't be null";
        String expectedPropertyPath = "expenseAmount";

        // when
        expenseResponseDTO = new ExpenseResponseDTO(expenseId, expenseName, expenseCategory, expenseAmount);
        Set<ConstraintViolation<ExpenseResponseDTO>> validationErrors = validator.validate(expenseResponseDTO);

        // then
        assertAll(
                () -> assertEquals(1, validationErrors.size()),
                () -> assertEquals(expectedErrorMessage, validationErrors.iterator().next().getMessage()),
                () -> assertEquals(expectedPropertyPath, validationErrors.iterator().next().getPropertyPath().toString())
        );
    }

    @Test
    public void should_ReturnFieldsCantBeNullErrors_When_ExpenseFieldsAreNull() {
        // given
        String expenseId = null;
        String expenseName = null;
        ExpenseCategory expenseCategory = null;
        BigDecimal expenseAmount = null;

        Set<String> expectedErrorMessagesList = new TreeSet<>(
                Arrays.asList("Can't be null"));
        Set<String> expectedPropertyPathsList = new TreeSet<>(
                Arrays.asList("id", "expenseName", "expenseCategory", "expenseAmount"));

        // when
        expenseResponseDTO = new ExpenseResponseDTO(expenseId, expenseName, expenseCategory, expenseAmount);
        Set<ConstraintViolation<ExpenseResponseDTO>> validationErrors = validator.validate(expenseResponseDTO);


        final Set<String> actualErrorMessagesList = new TreeSet<>(
                validationErrors.stream()
                        .map(n -> n.getMessage().toString())
                        .collect(Collectors.toSet()));
        final Set<String> actualPropertyPathsList = new TreeSet<>(
                validationErrors.stream()
                        .map(n -> n.getPropertyPath().toString())
                        .collect(Collectors.toSet())
        );

        // then
        assertAll(
                () -> assertEquals(4, validationErrors.size()),
                () -> assertIterableEquals(expectedErrorMessagesList, actualErrorMessagesList),
                () -> assertIterableEquals(expectedPropertyPathsList, actualPropertyPathsList)
        );
    }

}