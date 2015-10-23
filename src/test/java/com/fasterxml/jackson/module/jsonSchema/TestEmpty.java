package com.fasterxml.jackson.module.jsonSchema;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestEmpty extends SchemaTestBase {
    public void test() throws IOException {

        String input = "{}";

        ObjectMapper mapper = new ObjectMapper();

        JsonSchema schema = mapper.readValue(input, JsonSchema.class);
    }
}
