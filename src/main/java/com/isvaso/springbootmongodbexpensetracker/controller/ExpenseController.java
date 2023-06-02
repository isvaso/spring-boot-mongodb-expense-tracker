package com.isvaso.springbootmongodbexpensetracker.controller;

import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseAddRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseUpdateRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.service.ExpenseService;
import com.isvaso.springbootmongodbexpensetracker.util.ErrorUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The ExpenseController class handles HTTP requests related to expense.
 * It provides endpoints for adding, updating, retrieving, and deleting expenses.
 */
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    /**
     * Constructs a new ExpenseController with the given ExpenseService.
     */
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
        logger.info("ExpenseController initialized with ExpenseService: {}", expenseService);
    }

    /**
     * Adds a new expense to the system.
     *
     * @param expenseAddRequestDTO The DTO containing the details of the expense to be added.
     * @param bindingResult        The result of the validation of the request body.
     * @return A ResponseEntity representing the status of the operation.
     */
    @PostMapping
    public ResponseEntity<?> addExpense(
            @Valid @RequestBody ExpenseAddRequestDTO expenseAddRequestDTO,
            BindingResult bindingResult) {
        logger.debug("Adding new expense: {}", expenseAddRequestDTO);
        
        Optional<ResponseEntity<?>> responseEntity =
                ErrorUtils.getErrorResponse(bindingResult);

        if (responseEntity.isPresent()) {
            logger.info("Expense failed to validate");
            return responseEntity.get();
        }

        expenseService.addExpense(expenseAddRequestDTO);

        logger.info("Expense added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Updates an existing expense in the system.
     *
     * @param expenseUpdateRequestDTO The DTO containing the updated details of the expense.
     * @param bindingResult           The result of the validation of the request body.
     * @return A ResponseEntity representing the status of the operation.
     */
    @PutMapping
    public ResponseEntity<?> updateExpense(
            @Valid @RequestBody ExpenseUpdateRequestDTO expenseUpdateRequestDTO,
            BindingResult bindingResult) {
        logger.debug("Updating expense with ID: {}", expenseUpdateRequestDTO.id());

        Optional<ResponseEntity<?>> responseEntityOpt =
                ErrorUtils.getErrorResponse(bindingResult);

        if (responseEntityOpt.isPresent()) {
            logger.info("Expense update failed to validate");
            return responseEntityOpt.get();
        }

        expenseService.updateExpense(expenseUpdateRequestDTO);

        logger.info("Expense updated successfully");
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves all expenses from the system.
     *
     * @return A ResponseEntity containing a list of ExpenseResponseDTO representing all expenses.
     */
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        logger.debug("Retrieving all expenses");
        List<ExpenseResponseDTO> expenseResponseDTOList = expenseService.getAllExpenses();

        logger.info("All expenses retrieved successfully");
        return ResponseEntity.ok(expenseResponseDTOList);
    }

    /**
     * Retrieves an expense by its ID.
     *
     * @param id id The ID of the expense to retrieve.
     * @return A ResponseEntity containing the ExpenseResponseDTO representing the requested expense.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(
            @PathVariable String id) {
        logger.debug("Retrieving expense with ID: {}", id);
        ExpenseResponseDTO expense = expenseService.getExpenseById(id);

        logger.info("Expense with ID: {} retrieved successfully", id);
        return ResponseEntity.ok(expense);
    }

    /**
     * Deletes an expense by its ID.
     *
     * @param id The ID of the expense to delete.
     * @return A ResponseEntity containing the ExpenseResponseDTO representing the deleted expense.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> deleteExpense(
            @PathVariable String id) {
        logger.debug("Deleting expense with ID: {}", id);
        ExpenseResponseDTO deletedExpense = expenseService.deleteExpenseById(id);

        logger.info("Expense with ID: {} deleted successfully", id);
        return ResponseEntity.ok(deletedExpense);
    }
}
