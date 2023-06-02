package com.isvaso.springbootmongodbexpensetracker.controller;

import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseAddRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseResponseDTO;
import com.isvaso.springbootmongodbexpensetracker.dto.ExpenseUpdateRequestDTO;
import com.isvaso.springbootmongodbexpensetracker.model.ExpenseCategory;
import com.isvaso.springbootmongodbexpensetracker.service.ExpenseService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ExpenseControllerTest {

    @LocalServerPort
    private int port;

    private String url;

    @MockBean
    private ExpenseService expenseService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Nested
    class PostMapping {

        @Test
        public void should_CallAddExpenseMethod_When_AddExpense() {
            // given
            url = "http://localhost:" + port + "/api/expenses";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ExpenseAddRequestDTO expectedExpenseRequestDTO = new ExpenseAddRequestDTO(
                    "Discovery",
                    ExpenseCategory.ENTERTAINMENT.toString(),
                    "5.00"
            );

            HttpEntity<ExpenseAddRequestDTO> requestEntity = new HttpEntity<>(expectedExpenseRequestDTO, httpHeaders);

            // when
            testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

            // then
            verify(expenseService, times(1)).addExpense(expectedExpenseRequestDTO);
        }

        @Test
        public void should_ReturnStatusCreated_When_AddExpense() {
            // given
            url = "http://localhost:" + port + "/api/expenses";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ExpenseAddRequestDTO expectedExpenseRequestDTO = new ExpenseAddRequestDTO(
                    "Discovery",
                    ExpenseCategory.ENTERTAINMENT.toString(),
                    "5.00"
            );

            HttpEntity<ExpenseAddRequestDTO> requestEntity = new HttpEntity<>(expectedExpenseRequestDTO, httpHeaders);

            // when
            ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.POST, requestEntity, Void.class
            );

            // then
            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        }
    }

    @Nested
    class PutMapping {

        @Test
        public void should_CallUpdateExpenseMethod_When_UpdateExpense() {
            // given
            url = "http://localhost:" + port + "/api/expenses";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ExpenseUpdateRequestDTO expectedExpenseRequestDTO = new ExpenseUpdateRequestDTO(
                    "1",
                    "Discovery",
                    ExpenseCategory.ENTERTAINMENT.toString(),
                    "5.00"
            );

            HttpEntity<ExpenseUpdateRequestDTO> requestEntity = new HttpEntity<>(
                    expectedExpenseRequestDTO, httpHeaders);

            // when
            testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);

            // then
            verify(expenseService, times(1))
                    .updateExpense(expectedExpenseRequestDTO);
        }

        @Test
        public void should_ReturnStatusOk_When_UpdateExpense() {
            // given
            url = "http://localhost:" + port + "/api/expenses";
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ExpenseUpdateRequestDTO expectedExpenseRequestDTO = new ExpenseUpdateRequestDTO(
                    "1",
                    "Discovery",
                    ExpenseCategory.ENTERTAINMENT.toString(),
                    "5.00"
            );

            HttpEntity<ExpenseUpdateRequestDTO> requestEntity = new HttpEntity<>(
                    expectedExpenseRequestDTO, httpHeaders);

            // when
            ResponseEntity<Void> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.PUT, requestEntity, Void.class
            );

            // then
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        }
    }

    @Nested
    class GetMapping {

        @Test
        public void should_ReturnAllExpenses_When_GetAllExpenses() {
            // given
            url = "http://localhost:" + port + "/api/expenses";

            List<ExpenseResponseDTO> expectedExpenseList = Arrays.asList(
                    new ExpenseResponseDTO(
                            "1",
                            "FOX",
                            ExpenseCategory.ENTERTAINMENT,
                            BigDecimal.valueOf(11.00)
                    ),
                    new ExpenseResponseDTO(
                            "2",
                            "Water",
                            ExpenseCategory.GROCERIES,
                            BigDecimal.valueOf(3.00)
                    )
            );

            when(expenseService.getAllExpenses()).thenReturn(expectedExpenseList);

            // when
            ResponseEntity<List<ExpenseResponseDTO>> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );
            List<ExpenseResponseDTO> actualExpenseList = responseEntity.getBody();

            // then
            assertAll(
                    () -> assertEquals(2, actualExpenseList.size()),
                    () -> assertEquals(expectedExpenseList, actualExpenseList)
            );
        }

        @Test
        public void should_ReturnStatusOk_When_GetAllExpenses() {
            // given
            url = "http://localhost:" + port + "/api/expenses";

            when(expenseService.getAllExpenses()).thenReturn(new ArrayList<>());

            // when
            ResponseEntity<List<ExpenseResponseDTO>> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );

            // then
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        }
    }

    @Nested
    class GetMappingWithId {

        @Test
        public void should_ReturnExpense_When_GetExpenseById() {
            // given
            url = "http://localhost:" + port + "/api/expenses/1";

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTO(
                    "1",
                    "FOX",
                    ExpenseCategory.ENTERTAINMENT,
                    BigDecimal.valueOf(11.00)
            );

            when(expenseService.getExpenseById("1")).thenReturn(expectedExpense);

            // when
            ResponseEntity<ExpenseResponseDTO> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );
            ExpenseResponseDTO actualExpense = responseEntity.getBody();

            // then
            assertEquals(expectedExpense, actualExpense);

        }

        @ParameterizedTest
        @CsvSource(value = {"1", "abc", "0ab2145p", "000", "1_a_2asv"})
        public void should_ReturnExpense_When_MultipleGetExpenseById(String idValue) {
            // given
            url = "http://localhost:" + port + "/api/expenses/" + idValue;

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTO(
                    idValue,
                    "FOX",
                    ExpenseCategory.ENTERTAINMENT,
                    BigDecimal.valueOf(11.00)
            );

            when(expenseService.getExpenseById(idValue)).thenReturn(expectedExpense);

            // when
            ResponseEntity<ExpenseResponseDTO> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );
            ExpenseResponseDTO actualExpense = responseEntity.getBody();

            // then
            assertEquals(expectedExpense, actualExpense);

        }

        @Test
        public void should_ReturnStatusOk_When_GetExpenseById() {
            // given
            url = "http://localhost:" + port + "/api/expenses/1";

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTO(
                    "1",
                    "FOX",
                    ExpenseCategory.ENTERTAINMENT,
                    BigDecimal.valueOf(11.00)
            );

            when(expenseService.getExpenseById(anyString())).thenReturn(expectedExpense);

            // when
            ResponseEntity<ExpenseResponseDTO> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }
            );

            // then
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        }
    }

    @Nested
    class DeleteMappingWithID {

        @Test
        public void should_ReturnDeletedExpense_When_DeleteExpenseById() {
            // given
            url = "http://localhost:" + port + "/api/expenses/1";

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTO(
                    "1",
                    "FOX",
                    ExpenseCategory.ENTERTAINMENT,
                    BigDecimal.valueOf(11.00)
            );

            when(expenseService.deleteExpenseById("1")).thenReturn(expectedExpense);

            // when
            ResponseEntity<ExpenseResponseDTO> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {}
            );
            ExpenseResponseDTO actualExpense = responseEntity.getBody();

            // then
            assertEquals(expectedExpense, actualExpense);

        }

        @ParameterizedTest
        @CsvSource(value = {"1", "abc", "0ab2145p", "000", "1_a_2asv"})
        public void should_ReturnDeletedExpense_When_MultipleDeleteExpenseById(String idValue) {
            // given
            url = "http://localhost:" + port + "/api/expenses/" + idValue;

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTO(
                    idValue,
                    "FOX",
                    ExpenseCategory.ENTERTAINMENT,
                    BigDecimal.valueOf(11.00)
            );

            when(expenseService.deleteExpenseById(idValue)).thenReturn(expectedExpense);

            // when
            ResponseEntity<ExpenseResponseDTO> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {}
            );
            ExpenseResponseDTO actualExpense = responseEntity.getBody();

            // then
            assertEquals(expectedExpense, actualExpense);

        }

        @Test
        public void should_ReturnStatusOk_When_DeleteExpenseById() {
            // given
            url = "http://localhost:" + port + "/api/expenses/1";

            ExpenseResponseDTO expectedExpense = new ExpenseResponseDTO(
                    "1",
                    "FOX",
                    ExpenseCategory.ENTERTAINMENT,
                    BigDecimal.valueOf(11.00)
            );

            when(expenseService.deleteExpenseById(anyString())).thenReturn(expectedExpense);

            // when
            ResponseEntity<ExpenseResponseDTO> responseEntity = testRestTemplate.exchange(
                    url, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
                    }
            );

            // then
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        }
    }

}