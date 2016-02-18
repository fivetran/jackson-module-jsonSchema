package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.LateBindingDeserializer;

/**
 * This class is used during deserialization.
 * It captures the raw JSON value of the schema and the complete deserialization context.
 */
@JsonDeserialize(using = LateBindingDeserializer.class)
public class LateBindingSchema extends JsonSchema {
    public final ObjectNode json;
    public final DeserializationContext ctxt;
    public final ObjectCodec mapper;

    public LateBindingSchema(ObjectNode json, DeserializationContext ctxt, ObjectCodec codec) {
        this.json = json;
        this.ctxt = ctxt;
        this.mapper = codec;
    }

    @Override
    public JsonFormatTypes getType() {
        throw new RuntimeException("LateBindingSchema should only exist temporarily during the deserialization process");
    }
}
