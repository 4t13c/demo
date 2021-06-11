package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    private static final long serialVersionUID = 1L;

    public CustomLocalDateTimeDeserializer () {
        this(null);
    }

    protected CustomLocalDateTimeDeserializer (Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1)
            throws IOException, JsonProcessingException {
        return LocalDateTime.parse(arg0.getValueAsString(), DateTimeFormatter.ISO_DATE_TIME);
    }

}