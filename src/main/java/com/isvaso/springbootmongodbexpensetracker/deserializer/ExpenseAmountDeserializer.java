package com.isvaso.springbootmongodbexpensetracker.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ExpenseAmountDeserializer extends JsonDeserializer<String> {

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
