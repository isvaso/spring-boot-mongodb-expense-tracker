package com.isvaso.springbootmongodbexpensetracker.service;

import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseAddRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTOMapper;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseUpdateRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import com.isvaso.springbootmongodbexpensetracker.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class that provides methods for managing expenses.
 */
@Service
public class ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);

    private final ExpenseRepository expenseRepository;
    private final ExpenseResponseDTOMapper expenseResponseDTOMapper;

    /**
     * Constructs an instance of ExpenseService with the specified dependencies.
     *
     * @param expenseRepository        The expense repository to interact with the database.
     * @param expenseResponseDTOMapper The mapper to convert Expense objects to ExpenseResponseDTO objects.
     */
    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseResponseDTOMapper expenseResponseDTOMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseResponseDTOMapper = expenseResponseDTOMapper;
        logger.info("ExpenseService initialized with ExpenseRepository: {} and ExpenseResponseDTOMapper: {}",
                expenseRepository, expenseResponseDTOMapper);
    }

    /**
     * Adds an expense based on the provided ExpenseAddRequestDTO.
     *
     * @param expenseAddRequestDTO The ExpenseAddRequestDTO containing the details of the expense to add.
     */
    public void addExpense(ExpenseAddRequestDTO expenseAddRequestDTO) {
        Expense expense = new Expense(
                null,
                expenseAddRequestDTO.expenseName(),
                ExpenseCategory.valueOf(expenseAddRequestDTO.expenseCategory()),
                new BigDecimal(expenseAddRequestDTO.expenseAmount()));
        expenseRepository.insert(expense);
    }

    /**
     * Updates an existing expense based on the provided ExpenseUpdateRequestDTO.
     *
     * @param expenseUpdateRequestDTO The ExpenseUpdateRequestDTO containing the updated details of the expense.
     */
    public void updateExpense(ExpenseUpdateRequestDTO expenseUpdateRequestDTO) {
        Expense expense =
                expenseRepository.findById(expenseUpdateRequestDTO.id())
                        .orElseThrow(() -> new NoSuchElementException(
                                String.format("Expense with ID %s was not found",
                                        expenseUpdateRequestDTO.id())
                        ));

        logger.debug("Trying to update expense: {} by new info: {}", expense, expenseUpdateRequestDTO);

        boolean changesPresent = false;

        if (Objects.nonNull(expenseUpdateRequestDTO.expenseName())) {
            if (!expense.getExpenseName().equals(expenseUpdateRequestDTO.expenseName())) {
                expense.setExpenseName(expenseUpdateRequestDTO.expenseName());
                changesPresent = true;
                logger.info("Expense name change detected");
            }
        }

        if (Objects.nonNull(expenseUpdateRequestDTO.expenseCategory())) {
            if (Objects.isNull(expense.getExpenseCategory())
                    || !expense.getExpenseCategory()
                    .equals(ExpenseCategory.valueOf(expenseUpdateRequestDTO.expenseCategory()))) {
                expense.setExpenseCategory(
                        ExpenseCategory.valueOf(expenseUpdateRequestDTO.expenseCategory()));
                changesPresent = true;
                logger.info("Expense category change detected");
            }
        }

        if (Objects.nonNull(expenseUpdateRequestDTO.expenseAmount())) {
            BigDecimal newValue = new BigDecimal(expenseUpdateRequestDTO.expenseAmount());
            if (expense.getExpenseAmount()
                    .compareTo(newValue) != 0) {
                expense.setExpenseAmount(newValue);
                changesPresent = true;
                logger.info("Expense amount change detected");
            }
        }

        if (changesPresent) {
            expenseRepository.save(expense);
            logger.info("Changes have been made to Expense");
        }
    }

    /**
     * Retrieves all expenses and converts them to ExpenseResponseDTO objects.
     *
     * @return A list of ExpenseResponseDTO objects representing all expenses.
     */
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseResponseDTOMapper)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an expense by its unique identifier and converts it to an ExpenseResponseDTO object.
     *
     * @param id The unique identifier of the expense.
     * @return The ExpenseResponseDTO representing the expense with the specified ID.
     * @throws NoSuchElementException if no expense is found with the specified ID.
     */
    public ExpenseResponseDTO getExpenseById(String id) {
        return expenseRepository.findById(id)
                .map(expenseResponseDTOMapper)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Expense with ID %s was not found", id)
                ));
    }

    /**
     * Deletes an expense by its unique identifier and return it as an ExpenseResponseDTO object.
     *
     * @param id The unique identifier of the expense to delete.
     * @return The ExpenseResponseDTO representing the deleted expense.
     * @throws NoSuchElementException if no expense is found with the specified ID.
     */
    public ExpenseResponseDTO deleteExpenseById(String id) {
        return expenseRepository.deleteExpenseById(id)
                .map(expenseResponseDTOMapper)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Expense with ID %s was not found", id)
                ));
    }
}
