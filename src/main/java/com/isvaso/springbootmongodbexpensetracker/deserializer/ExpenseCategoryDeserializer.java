package com.isvaso.springbootmongodbexpensetracker.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *  Custom deserializer for deserializing expense category from JSON.
 *  This deserializer converts expense category to uppercase.
 */
public class ExpenseCategoryDeserializer extends JsonDeserializer<String> {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseCategoryDeserializer.class);

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
        logger.debug("Trying to deserialize and uppercase of {} to String", jsonParser.getText());

        String category = jsonParser.getText();
        category = category.toUpperCase();

        logger.info("Deserialization and conversion of {} to an uppercase String was successful", category);
        return category;
    }
}
