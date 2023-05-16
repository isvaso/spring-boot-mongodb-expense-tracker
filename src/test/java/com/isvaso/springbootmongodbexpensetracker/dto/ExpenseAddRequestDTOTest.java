package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import com.isvaso.springbootmongodbexpensetracker.testutil.StringGenerator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseAddRequestDTOTest {

    private Validator validator;

    private ExpenseAddRequestDTO expenseAddRequestDTO;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    class AllFieldsAtTheSameTimeTests {

        @Test
        public void should_NotReturnFieldErrors_When_FieldsAreFilledCorrectly() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            // then
            assertTrue(validationErrors.isEmpty());
        }
    }

    @Nested
    class ExpenseNameFieldTests {

        @Test
        public void should_ReturnCantBeNullAndBlankErrors_When_FiledIsNull() {
            // given
            String expenseName = null;
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Can't be null", "Can't be blank")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseName")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(2, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }

        @Test
        public void should_ReturnCantBeBlankAndLengthError_When_FiledIsEmpty() {
            // given
            String expenseName = "";
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Can't be blank", "Length must be between 1 and 255")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseName")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(2, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }

        @Test
        public void should_ReturnExpenseNameLengthError_When_ExpenseNameIsTooLong() {
            // given
            String expenseName = StringGenerator.generate(256);
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Length must be between 1 and 255")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseName")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(1, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }
    }

    @Nested
    class ExpenseCategoryFieldTests {

        @Test
        public void should_ReturnCantBeNullAndMustBeAnyOfErrors_When_ExpenseCategoryIsNull() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = null;
            String expenseAmount = "22.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Can't be null", "Must be any of ExpenseCategory")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseCategory")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(2, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }

        @Test
        public void should_ReturnMustBeAnyOfExpenseCategoryError_When_ExpenseCategoryIsNot() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = "ABC";
            String expenseAmount = "22.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Must be any of ExpenseCategory")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseCategory")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(1, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }
    }

    @Nested
    class ExpenseAmountFieldTests {

        @Test
        public void should_ReturnCantBeNullError_When_FieldIsNull() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.ENTERTAINMENT.toString();
            String expenseAmount = null;

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("ExpenseAmount can't be null")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseAmount")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(1, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }

        @Test
        public void should_StringIsBigDecimalError_When_FieldIsNotDigit() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.ENTERTAINMENT.toString();
            String expenseAmount = "ABC";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList(
                            "Must be a positive digit",
                            "ExpenseAmount can not be less than 0.00",
                            "ExpenseAmount can not be more than 999999.99")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseAmount")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(3, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }

        @Test
        public void should_DecimalMinError_When_FieldIsNegativeDigit() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.ENTERTAINMENT.toString();
            String expenseAmount = "-1";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("ExpenseAmount can not be less than 0.00")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseAmount")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(1, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }

        @Test
        public void should_DecimalMaxError_When_FieldIsTooBigDigit() {
            // given
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.ENTERTAINMENT.toString();
            String expenseAmount = "1000000000.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("ExpenseAmount can not be more than 999999.99")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseAmount")
            );

            // when
            expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseAddRequestDTO>> validationErrors =
                    validator.validate(expenseAddRequestDTO);

            final Set<String> actualErrorMessagesList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getMessage().toString())
                            .collect(Collectors.toSet())
            );

            final Set<String> actualPropertyPathsList = new TreeSet<>(
                    validationErrors.stream()
                            .map(n -> n.getPropertyPath().toString())
                            .collect(Collectors.toSet())
            );

            // then
            assertAll(
                    () -> assertEquals(1, validationErrors.size()),
                    () -> assertEquals(expectedErrorMessagesList, actualErrorMessagesList),
                    () -> assertEquals(expectedPropertyPathsList, actualPropertyPathsList)
            );
        }
    }

}