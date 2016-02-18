package com.fasterxml.jackson.module.jsonSchema;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.types.LateBindingSchema;

import java.io.IOException;

public class LateBindingDeserializer extends JsonDeserializer<LateBindingSchema> {
    @Override
    public LateBindingSchema deserialize(JsonParser p,
                                         DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectNode json = p.readValueAs(ObjectNode.class);
        ObjectCodec codec = p.getCodec();

        return new LateBindingSchema(json, ctxt, codec);
    }
}
