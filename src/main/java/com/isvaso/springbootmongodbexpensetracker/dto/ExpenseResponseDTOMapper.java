package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * A mapper class that converts an {@link Expense} object to an {@link ExpenseResponseDTO} object.
 */
@Service
public class ExpenseResponseDTOMapper
        implements Function<Expense, ExpenseResponseDTO> {

    /**
     * Converts an {@link Expense} object to an {@link ExpenseResponseDTO} object.
     *
     * @param expense The Expense object to be converted.
     * @return An ExpenseResponseDTO object representing the converted Expense object.
     */
    @Override
    public ExpenseResponseDTO apply(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getExpenseName(),
                expense.getExpenseCategory(),
                expense.getExpenseAmount()
        );
    }
}
