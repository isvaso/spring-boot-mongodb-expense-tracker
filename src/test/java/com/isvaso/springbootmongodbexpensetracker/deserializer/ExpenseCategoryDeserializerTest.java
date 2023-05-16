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
import static org.mockito.Mockito.when;

class ExpenseCategoryDeserializerTest {

    @Mock
    private JsonParser jsonParserMock;

    @Mock
    private DeserializationContext deserializationContextMock;

    private ExpenseCategoryDeserializer expenseCategoryDeserializer;

    public ExpenseCategoryDeserializerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUp() {
        expenseCategoryDeserializer = new ExpenseCategoryDeserializer();
    }

    @Test
    public void should_ReturnUpperCaseText_When_TextIsExists() throws IOException {
        // given
        String sourceText = "text";
        String expectedText = "TEXT";
        when(jsonParserMock.getText()).thenReturn(sourceText);

        // when
        String actualText = expenseCategoryDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expectedText, actualText);
    }

    @ParameterizedTest
    @CsvSource(value = {"text, TEXT", "1, 1", "Some text, SOME TEXT", "SoMe_TeXt!, SOME_TEXT!"})
    public void should_ReturnUpperCaseTexts_When_MultipleTexts(String sourceText,
                                                               String expectedText) throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn(sourceText);

        // when
        String actualText = expenseCategoryDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertEquals(expectedText, actualText);
    }

    @Test
    public void should_ThrowNullPointerException_When_ValueIsNull() throws IOException {
        // given
        when(jsonParserMock.getText()).thenReturn(null);

        // when
        Executable executable = () -> expenseCategoryDeserializer
                .deserialize(jsonParserMock, deserializationContextMock);

        // then
        assertThrows(NullPointerException.class, executable);
    }

}