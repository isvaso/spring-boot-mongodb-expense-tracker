package com.isvaso.springbootmongodbexpensetracker.service;

import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseAddRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTOMapper;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseUpdateRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.model.Expense;
import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import com.isvaso.springbootmongodbexpensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Nested
    class AddExpenseMethodTests {

        @InjectMocks
        private ExpenseService expenseService;

        @Mock
        private ExpenseRepository expenseRepositoryMock;

        @Spy
        private ExpenseResponseDTOMapper expenseResponseDTOMapperMock;

        @Captor
        private ArgumentCaptor<Expense> expenseArgumentCaptor;

        @Test
        public void should_CallRepositoryInsertMethod_When_AddExpense() {
            // given
            ExpenseAddRequestDTO expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    "Spotify",
                    ExpenseCategory.ENTERTAINMENT.toString(),
                    "5.00");

            // when
            expenseService.addExpense(expenseAddRequestDTO);

            // then
            verify(expenseRepositoryMock, times(1)).insert(any(Expense.class));
        }

        @Test
        public void should_InsertExpectedExpense_When_AddExpense() {
            // given
            ExpenseAddRequestDTO expenseAddRequestDTO = new ExpenseAddRequestDTO(
                    "Spotify",
                    ExpenseCategory.ENTERTAINMENT.toString(),
                    "5.00");

            Expense expectedExpense = new Expense(
                    null,
                    expenseAddRequestDTO.expenseName(),
                    ExpenseCategory.valueOf(expenseAddRequestDTO.expenseCategory()),
                    new BigDecimal(expenseAddRequestDTO.expenseAmount())
            );

            // when
            expenseService.addExpense(expenseAddRequestDTO);

            // then
            verify(expenseRepositoryMock, times(1)).insert(expenseArgumentCaptor.capture());
            Expense actualExpense = expenseArgumentCaptor.getValue();

            assertEquals(expectedExpense, actualExpense);
        }

        @Test
        public void should_InsertExpectedExpenses_When_AddMultiplyTimes() {
            // given
            List<ExpenseAddRequestDTO> expenseAddRequestDTOList = Arrays.asList(
                    new ExpenseAddRequestDTO(
                            "Spotify",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "5.00"),
                    new ExpenseAddRequestDTO(
                            "AppleMusic",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "6.00"),
                    new ExpenseAddRequestDTO(
                            "AppleTV",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "7.00")
            );

            List<Expense> expectedExpensesList = expenseAddRequestDTOList.stream()
                    .map(n -> {
                        return new Expense(
                                null,
                                n.expenseName(),
                                ExpenseCategory.valueOf(n.expenseCategory()),
                                new BigDecimal(n.expenseAmount())
                        );
                    })
                    .collect(Collectors.toList());

            // when
            expenseService.addExpense(expenseAddRequestDTOList.get(0));
            expenseService.addExpense(expenseAddRequestDTOList.get(1));
            expenseService.addExpense(expenseAddRequestDTOList.get(2));

            // then
            verify(expenseRepositoryMock, times(3)).insert(expenseArgumentCaptor.capture());
            List<Expense> actualExpensesList = expenseArgumentCaptor.getAllValues();

            assertEquals(expectedExpensesList, actualExpensesList);
        }
    }

    @Nested
    class UpdateExpenseMethodTests {

        @InjectMocks
        private ExpenseService expenseService;

        @Mock
        private ExpenseRepository expenseRepositoryMock;

        @Spy
        private ExpenseResponseDTOMapper expenseResponseDTOMapperMock;

        @Test
        public void should_ThrowNoSuchElementException_When_ExpenseWasNotFound() {
            // given
            ExpenseUpdateRequestDTO expenseUpdateRequestDTOMock =
                    new ExpenseUpdateRequestDTO(
                            "1",
                            "ExpenseName",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "5.00"
                    );

            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.empty());

            // when
            Executable executable = () -> expenseService
                    .updateExpense(expenseUpdateRequestDTOMock);

            // then
            assertThrows(NoSuchElementException.class, executable);
        }

        @Test
        public void should_ThrowRuntimeException_When_ChangesPresent() {
            // given
            ExpenseUpdateRequestDTO expenseUpdateRequestDTOMock =
                    new ExpenseUpdateRequestDTO(
                            "1",
                            "ExpenseNewName",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "5.00"
                    );

            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.of(new Expense(
                                    "1",
                                    "ExpenseOldName",
                                    ExpenseCategory.ENTERTAINMENT,
                                    new BigDecimal(5.00)
                            )
                    ));
            when(expenseRepositoryMock.save(any())).thenThrow(new RuntimeException());

            // when
            Executable executable = () -> expenseService.updateExpense(expenseUpdateRequestDTOMock);

            // then
            assertThrows(RuntimeException.class, executable);
        }

        @Test
        public void should_CallSaveMethod_When_NameWasChanged() {
            // given
            ExpenseUpdateRequestDTO expenseUpdateRequestDTOMock =
                    new ExpenseUpdateRequestDTO(
                            "1",
                            "ExpenseNewName",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "5.00"
                    );

            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.of(
                            new Expense(
                                    "1",
                                    "ExpenseOldName",
                                    ExpenseCategory.ENTERTAINMENT,
                                    new BigDecimal(5.00)
                            )
                    ));

            // when
            expenseService.updateExpense(expenseUpdateRequestDTOMock);

            // then
            verify(expenseRepositoryMock, times(1)).save(any());
            verifyNoMoreInteractions(expenseRepositoryMock);
        }

        @Test
        public void should_CallSaveMethod_When_CategoryWasChanged() {
            // given
            ExpenseUpdateRequestDTO expenseUpdateRequestDTOMock =
                    new ExpenseUpdateRequestDTO(
                            "1",
                            "ExpenseName",
                            ExpenseCategory.MISC.toString(),
                            "5.00"
                    );

            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.of(
                            new Expense(
                                    "1",
                                    "ExpenseName",
                                    ExpenseCategory.ENTERTAINMENT,
                                    new BigDecimal(5.00)
                            )
                    ));

            // when
            expenseService.updateExpense(expenseUpdateRequestDTOMock);

            // then
            verify(expenseRepositoryMock, times(1)).save(any());
            verifyNoMoreInteractions(expenseRepositoryMock);
        }

        @Test
        public void should_CallSaveMethod_When_PriceWasChanged() {
            // given
            ExpenseUpdateRequestDTO expenseUpdateRequestDTOMock =
                    new ExpenseUpdateRequestDTO(
                            "1",
                            "ExpenseName",
                            ExpenseCategory.ENTERTAINMENT.toString(),
                            "7.00"
                    );

            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.of(
                            new Expense(
                                    "1",
                                    "ExpenseName",
                                    ExpenseCategory.ENTERTAINMENT,
                                    new BigDecimal(5.00)
                            )
                    ));

            // when
            expenseService.updateExpense(expenseUpdateRequestDTOMock);

            // then
            verify(expenseRepositoryMock, times(1)).save(any());
            verifyNoMoreInteractions(expenseRepositoryMock);
        }

    }

    @Nested
    class GetAllExpensesMethodTests {

        @InjectMocks
        private ExpenseService expenseService;

        @Mock
        private ExpenseRepository expenseRepositoryMock;

        @Spy
        private ExpenseResponseDTOMapper expenseResponseDTOMapperMock;

        @Test
        public void should_CallFindAll_When_CallGetAllExpenses() {
            // given
            List<Expense> emptyList = new ArrayList<>();
            when(expenseRepositoryMock.findAll()).thenReturn(emptyList);

            // when
            expenseService.getAllExpenses();

            // then
            verify(expenseRepositoryMock, times(1)).findAll();
            verifyNoMoreInteractions(expenseRepositoryMock);
        }

        @Test
        public void should_ReturnEmptyList_When_NoExpenses() {
            // given
            List<Expense> emptyList = new ArrayList<>();
            when(expenseRepositoryMock.findAll()).thenReturn(emptyList);

            // when
            List<ExpenseResponseDTO> actualList = expenseService.getAllExpenses();

            // then
            assertEquals(0, actualList.size());
        }

        @Test
        public void should_ReturnListWithExpenses_When_ExpensesThereIs() {
            // given
            List<Expense> expenseList = Arrays.asList(
                    new Expense(
                            "1",
                            "ExpenseFirst",
                            ExpenseCategory.MISC,
                            new BigDecimal("12.00")
                    ),
                    new Expense(
                            "2",
                            "ExpenseSecond",
                            ExpenseCategory.ENTERTAINMENT,
                            new BigDecimal("8.00")
                    ),
                    new Expense(
                            "3",
                            "ExpenseThird",
                            ExpenseCategory.GROCERIES,
                            new BigDecimal("24.00")
                    )
            );
            when(expenseRepositoryMock.findAll()).thenReturn(expenseList);

            List<ExpenseResponseDTO> expectedList = expenseList.stream()
                    .map(expenseResponseDTOMapperMock)
                    .collect(Collectors.toList());

            // when
            List<ExpenseResponseDTO> actualList = expenseService.getAllExpenses();

            // then
            assertEquals(expectedList, actualList);
        }
    }

    @Nested
    class GetExpenseByIdMethodTests {

        @InjectMocks
        private ExpenseService expenseService;

        @Mock
        private ExpenseRepository expenseRepositoryMock;

        @Spy
        private ExpenseResponseDTOMapper expenseResponseDTOMapperMock;

        @Test
        public void should_ThrowNoSuchElementException_When_ExpenseWasNotFound() {
            // given
            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.empty());

            // when
            Executable executable = () -> expenseService
                    .getExpenseById("2");

            // then
            assertThrows(NoSuchElementException.class, executable);
        }

        @Test
        public void should_ReturnExpense_When_ExpenseThereIs() {
            // given
            Expense expense = new Expense(
                    "1",
                    "ExpenseFirst",
                    ExpenseCategory.MISC,
                    new BigDecimal("12.00")
            );
            when(expenseRepositoryMock.findById(anyString()))
                    .thenReturn(Optional.of(expense));

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTOMapper().apply(expense);

            // when
            ExpenseResponseDTO actualExpense = expenseService.getExpenseById("1");

            // then
            assertEquals(expectedExpense, actualExpense);
        }
    }

    @Nested
    class DeleteExpenseByIdMethodTests {

        @InjectMocks
        private ExpenseService expenseService;

        @Mock
        private ExpenseRepository expenseRepositoryMock;

        @Spy
        private ExpenseResponseDTOMapper expenseResponseDTOMapperMock;

        @Test
        public void should_CallDeleteExpenseById_When_CallDeleteExpenseById() {
            // given
            Expense expense = new Expense(
                    "1",
                    "ExpenseFirst",
                    ExpenseCategory.MISC,
                    new BigDecimal("12.00")
            );
            when(expenseRepositoryMock.deleteExpenseById(anyString()))
                    .thenReturn(Optional.of(expense));

            // when
            expenseService.deleteExpenseById(anyString());

            // then
            verify(expenseRepositoryMock, times(1))
                    .deleteExpenseById(anyString());
            verifyNoMoreInteractions(expenseRepositoryMock);
        }

        @Test
        public void should_ThrowNoSuchElementException_When_ExpenseWasNotFound() {
            // given
            when(expenseRepositoryMock.deleteExpenseById(anyString()))
                    .thenReturn(Optional.empty());

            // when
            Executable executable = () -> expenseService
                    .deleteExpenseById("2");

            // then
            assertThrows(NoSuchElementException.class, executable);
        }

        @Test
        public void should_ReturnExpense_When_ExpenseHasBeenDeleted() {
            // given
            Expense expense = new Expense(
                    "1",
                    "ExpenseFirst",
                    ExpenseCategory.MISC,
                    new BigDecimal("12.00")
            );
            when(expenseRepositoryMock.deleteExpenseById(anyString()))
                    .thenReturn(Optional.of(expense));

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTOMapper().apply(expense);

            // when
            ExpenseResponseDTO actualExpense = expenseService.deleteExpenseById("1");

            // then
            assertEquals(expectedExpense, actualExpense);
        }
    }
}