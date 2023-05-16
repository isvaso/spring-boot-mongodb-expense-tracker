package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import com.isvaso.springbootmongodbexpensetracker.testutil.StringGenerator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseUpdateRequestDTOTest {

    private Validator validator;

    private ExpenseUpdateRequestDTO expenseUpdateRequestDTO;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Nested
    class AllFieldsAtTheSameTimeTests {

        @Test
        public void should_NotReturnFieldErrors_When_FieldsAreFilledCorrectly() {
            // given
            String expenseId = "1";
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            // when
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

            // then
            assertTrue(validationErrors.isEmpty());
        }
    }

    @Nested
    class ExpenseIdFieldTests {

        @Test
        public void should_ReturnCantBeNullAndEmptyErrors_When_FiledIsNull() {
            // given
            String expenseId = null;
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Can't be null", "Can't be empty")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("id")
            );

            // when
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
        public void should_ReturnCantEmptyError_When_FiledIsEmpty() {
            // given
            String expenseId = "";
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Can't be empty")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("id")
            );

            // when
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
    class ExpenseNameFieldTests {

        @Test
        public void should_ReturnCantBeBlankError_When_FiledIsNull() {
            // given
            String expenseId = "1";
            String expenseName = null;
            String expenseCategory = ExpenseCategory.MISC.toString();
            String expenseAmount = "19.00";

            Set<String> expectedErrorMessagesList = new TreeSet<>(
                    Arrays.asList("Can't be blank")
            );
            Set<String> expectedPropertyPathsList = new TreeSet<>(
                    Arrays.asList("expenseName")
            );

            // when
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
        public void should_ReturnCantBeBlankAndLengthError_When_FiledIsEmpty() {
            // given
            String expenseId = "1";
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
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
            String expenseId = "1";
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
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
        public void should_NotReturnError_When_ExpenseCategoryIsNull() {
            // given
            String expenseId = "1";
            String expenseName = "ExpenseName";
            String expenseCategory = null;
            String expenseAmount = "22.00";

            // when
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

            // then
            assertTrue(validationErrors.isEmpty());
        }

        @Test
        public void should_ReturnMustBeAnyOfExpenseCategoryError_When_ExpenseCategoryIsNot() {
            // given
            String expenseId = "1";
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
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
        public void should_NotReturnError_When_FieldIsNull() {
            // given
            String expenseId = "1";
            String expenseName = "ExpenseName";
            String expenseCategory = ExpenseCategory.ENTERTAINMENT.toString();
            String expenseAmount = null;

            // when
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

            // then
            assertTrue(validationErrors.isEmpty());
        }

        @Test
        public void should_StringIsBigDecimalError_When_FieldIsNotDigit() {
            // given
            String expenseId = "1";
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
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
            String expenseId = "1";
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
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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
            String expenseId = "1";
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
            expenseUpdateRequestDTO = new ExpenseUpdateRequestDTO(
                    expenseId, expenseName, expenseCategory, expenseAmount);

            Set<ConstraintViolation<ExpenseUpdateRequestDTO>> validationErrors =
                    validator.validate(expenseUpdateRequestDTO);

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