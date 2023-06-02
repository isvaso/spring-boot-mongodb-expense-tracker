package com.isvaso.springbootmongodbexpensetracker.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Custom deserializer for deserializing expense amount from JSON.
 * This deserializer is used to handle empty or whitespace expense amounts.
 */
public class ExpenseAmountDeserializer extends JsonDeserializer<String> {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseAmountDeserializer.class);

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
        logger.debug("Trying to deserialize of {} to String", jsonParser.getText());

        String value = jsonParser.getText();

        if (value.trim().isEmpty()) {
            logger.info("Empty expense amount encountered during deserialization");
            return " ";
        }

        logger.info("Deserialization of {} to String was successful", jsonParser.getText());
        return value;
    }
}
