package com.isvaso.springbootmongodbexpensetracker.dto;

import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExpenseResponseDTOMapper
        implements Function<Expense, ExpenseResponseDTO> {

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
