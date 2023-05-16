package com.isvaso.springbootmongodbexpensetracker.controller;

import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseAddRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseUpdateRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.service.ExpenseService;
import com.isvaso.springbootmongodbexpensetracker.util.ErrorUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<?> addExpense(
            @Valid @RequestBody ExpenseAddRequestDTO ExpenseAddRequestDTO,
            BindingResult bindingResult) {

        Optional<ResponseEntity<?>> responseEntity =
                ErrorUtils.getErrorResponse(bindingResult);

        if (responseEntity.isPresent()) {
            return responseEntity.get();
        }

        expenseService.addExpense(ExpenseAddRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateExpense(
            @Valid @RequestBody ExpenseUpdateRequestDTO expenseUpdateRequestDTO,
            BindingResult bindingResult) {

        Optional<ResponseEntity<?>> responseEntityOpt =
                ErrorUtils.getErrorResponse(bindingResult);

        if (responseEntityOpt.isPresent()) {
            return responseEntityOpt.get();
        }

        expenseService.updateExpense(expenseUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(
            @PathVariable String id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> deleteExpense(
            @PathVariable String id) {
        return ResponseEntity.ok(expenseService.deleteExpenseById(id));
    }
}
