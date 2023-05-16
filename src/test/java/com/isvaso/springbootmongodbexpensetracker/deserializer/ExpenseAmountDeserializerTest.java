package com.isvaso.springbootmongodbexpensetracker.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseAmountDeserializerTest {

    @Mock
    private JsonParser jsonParserMock;

    @Mock
    private DeserializationContext deserializationContextMock;

    private ExpenseAmountDeserializer expenseAmountDeserializer;

    public ExpenseAmountDeserializerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUp() {
        expenseAmountDeserializer = new ExpenseAmountDeserializer();
    }

    @Test
    public void should_ReturnValue_When_ValueExists() throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn("55.00");
        String expected = "55.00";

        // when
        String actual = expenseAmountDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource(value = {"1", "A", "99.00", "0A"})
    public void should_ReturnValue_When_MultipleValueExists(String value) throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn(value);
        String expected = value;

        // when
        String actual = expenseAmountDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValueWithSpace_When_ValueIsEmpty() throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn("");
        String expected = " ";

        // when
        String actual = expenseAmountDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValueWithSpace_When_ValueIsSingleSpace() throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn(" ");
        String expected = " ";

        // when
        String actual = expenseAmountDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnValueWithSpace_When_ValueIsMultipleSpaces() throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn("             ");
        String expected = " ";

        // when
        String actual = expenseAmountDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void should_ThrowNullPointerException_When_ValueIsNull() throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn(null);

        // when
        Executable executable = () -> expenseAmountDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertThrows(NullPointerException.class, executable);
    }
}