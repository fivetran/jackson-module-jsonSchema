package com.fasterxml.jackson.module.jsonSchema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This class is used during deserialization.
 * The @JsonCreator factory method on JsonSchema takes a DynamicSchema,
 * so Jackson invokes DynamicSchemaDeserializer.
 *
 * The reason we use this roundabout method is so that we can get JsonParser and DeserializationContext,
 * which are not available in @JsonCreator.
 */
@JsonDeserialize(using = DynamicSchemaDeserializer.class)
public class DynamicSchema {
    public final JsonSchema result;

    public DynamicSchema(JsonSchema result) {
        this.result = result;
    }
}
