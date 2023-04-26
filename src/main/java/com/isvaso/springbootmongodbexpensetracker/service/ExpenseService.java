package com.isvaso.springbootmongodbexpensetracker.service;

import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseAddRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTOMapper;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseUpdateRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import com.isvaso.springbootmongodbexpensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseResponseDTOMapper expenseResponseDTOMapper;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseResponseDTOMapper expenseResponseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseResponseDTOMapper = expenseResponseDTOMapper;
    }

    public void addExpense(ExpenseAddRequestDTO expenseAddRequestDTO) {
        Expense expense = new Expense(
                null,
                expenseAddRequestDTO.expenseName(),
                ExpenseCategory.valueOf(expenseAddRequestDTO.expenseCategory()),
                new BigDecimal(expenseAddRequestDTO.expenseAmount()));
        expenseRepository.insert(expense);
    }

    public void updateExpense(ExpenseUpdateRequestDTO expenseUpdateRequestDTO) {
        Expense expense =
                expenseRepository.findById(expenseUpdateRequestDTO.id())
                        .orElseThrow(() -> new NoSuchElementException(
                                String.format("Expense with ID %s was not found",
                                        expenseUpdateRequestDTO.id())
                        ));

        boolean changesPresent = false;

        if (Objects.nonNull(expenseUpdateRequestDTO.expenseName())) {
            if (!expense.getExpenseName().equals(expenseUpdateRequestDTO.expenseName())) {
                expense.setExpenseName(expenseUpdateRequestDTO.expenseName());
                changesPresent = true;
            }
        }

        if (Objects.nonNull(expenseUpdateRequestDTO.expenseCategory())) {
            if (Objects.isNull(expense.getExpenseCategory())
                    || !expense.getExpenseCategory()
                    .equals(ExpenseCategory.valueOf(expenseUpdateRequestDTO.expenseCategory()))) {
                expense.setExpenseCategory(
                        ExpenseCategory.valueOf(expenseUpdateRequestDTO.expenseCategory()));
                changesPresent = true;
            }
        }

        if (Objects.nonNull(expenseUpdateRequestDTO.expenseAmount())) {
            BigDecimal newValue = new BigDecimal(expenseUpdateRequestDTO.expenseAmount());
            if (expense.getExpenseAmount()
                    .compareTo(newValue) != 0) {
                expense.setExpenseAmount(newValue);
                changesPresent = true;
            }
        }

        if (changesPresent) {
            try {
                expenseRepository.save(expense);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseResponseDTOMapper)
                .collect(Collectors.toList());
    }

    public ExpenseResponseDTO getExpenseById(String id) {
        return expenseRepository.findById(id)
                .map(expenseResponseDTOMapper)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Expense with ID %s was not found", id)
                ));
    }

    public ExpenseResponseDTO deleteExpenseById(String id) {
        return expenseRepository.deleteExpenseById(id)
                .map(expenseResponseDTOMapper)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Expense with ID %s was not found", id)
                ));
    }
}
