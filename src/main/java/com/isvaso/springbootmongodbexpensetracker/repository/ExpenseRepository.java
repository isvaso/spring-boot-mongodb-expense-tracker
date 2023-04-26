package com.isvaso.springbootmongodbexpensetracker.repository;

import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<Expense, String> {

    Optional<Expense> deleteExpenseById(String id);
}
