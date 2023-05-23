package com.isvaso.springbootmongodbexpensetracker.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Custom deserializer for deserializing expense amount from JSON.
 * This deserializer is used to handle empty or whitespace expense amounts.
 */
public class ExpenseAmountDeserializer extends JsonDeserializer<String> {

    /**
     * Deserializes the expense amount from JSON.
     *
     * @param jsonParser The JSON parser.
     * @param context The deserialization context.
     * @return The deserialized expense amount as a string.
     * @throws IOException If an I/O error occurs during deserialization.
     */
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext context)
            throws IOException {
        String value = jsonParser.getText();

        if (value.trim().isEmpty()) {
            return " ";
        }

        return value;
    }
}
