package com.isvaso.springbootmongodbexpensetracker.repository;

import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Provides methods for working with the MongoDB database containing Expenses.
 * This interface extends the MongoRepository interface and provides default methods for basic CRUD operations on Expense entities,
 * as well as a custom method for retrieving an Expense by its unique identifier.
 */
public interface ExpenseRepository extends MongoRepository<Expense, String> {

    /**
     * Retrieves an Expense by its unique identifier.
     *
     * @param id The unique identifier of the Expense.
     * @return An Optional containing the Expense object if an expense was found, or an empty Optional if no expense was found.
     * @throws Exception if an error occurs during the retrieval process.
     */
    Optional<Expense> deleteExpenseById(String id);
}
