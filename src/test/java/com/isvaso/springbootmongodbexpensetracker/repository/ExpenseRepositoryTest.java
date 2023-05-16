package com.isvaso.springbootmongodbexpensetracker.repository;

import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository expenseRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", () -> "localhost");
        registry.add("spring.data.mongodb.port", () -> "27017");
    }

    @AfterEach
    void cleanUp() {
        this.expenseRepository.deleteAll();
    }

    @Test
    void should_ReturnDeletedExpense_When_DeleteExpenseById() {
        // given
        this.expenseRepository.insert(new Expense(
                "1",
                "Discovery",
                ExpenseCategory.ENTERTAINMENT,
                BigDecimal.valueOf(9.00)
        ));
        this.expenseRepository.insert(new Expense(
                "2",
                "Burger King",
                ExpenseCategory.RESTAURANT,
                BigDecimal.valueOf(44.00)
        ));

        Expense expectedExpense = new Expense(
                "3",
                "Toilet Paper",
                ExpenseCategory.MISC,
                BigDecimal.valueOf(1.00)
        );
        this.expenseRepository.insert(expectedExpense);

        // when
        Optional<Expense> optionalExpense = expenseRepository.deleteExpenseById("3");
        Expense actualExpense = optionalExpense.get();

        // then
        assertEquals(expectedExpense, actualExpense);
    }

    @Test
    void should_ReturnEmptyOptional_When_DeleteExpenseByIdInEmptyDataBase() {
        // given

        // when
        Optional<Expense> optionalExpense = expenseRepository.deleteExpenseById("1");

        // then
        assertTrue(optionalExpense.isEmpty());
    }

}