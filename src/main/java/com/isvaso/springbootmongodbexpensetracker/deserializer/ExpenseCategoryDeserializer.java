package com.isvaso.springbootmongodbexpensetracker.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 *  Custom deserializer for deserializing expense category from JSON.
 *  This deserializer converts expense category to uppercase.
 */
public class ExpenseCategoryDeserializer extends JsonDeserializer<String> {

    /**
     * Deserializes expense category from JSON and converts to uppercase.
     *
     * @param jsonParser The JSON parser.
     * @param deserializationContext The deserialization context.
     * @return The deserialized expense category as a string in uppercase.
     * @throws IOException If an I/O error occurs during deserialization.
     */
    @Override
    public String deserialize(JsonParser jsonParser,
                              DeserializationContext deserializationContext)
            throws IOException {
        return jsonParser.getText().toUpperCase();
    }
}
