package com.fasterxml.jackson.module.jsonSchema;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.types.*;

import java.io.IOException;
import java.util.Arrays;

public class DynamicSchemaDeserializer extends JsonDeserializer<DynamicSchema> {
    @Override
    public DynamicSchema deserialize(JsonParser p,
                                     DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new DynamicSchema(readDynamically(p, ctxt));
    }

    private static JsonSchema readDynamically(JsonParser p,
                                              DeserializationContext ctxt) throws IOException {
        ObjectCodec mapper = p.getCodec();
        ObjectNode json = p.readValueAs(ObjectNode.class);

        if (json == null)
            return new NullSchema();
        else if (json.has("type")) {
            JsonNode type = json.get("type");

            if (type.isArray()) {
                JsonSchema[] anyOf = new JsonSchema[type.size()];

                for (int i = 0; i < type.size(); i++) {
                    String typeId = type.get(i).asText();
                    JsonSchema schema = newInstanceFromId(typeId);

                    anyOf[i] = schema;
                }

                UnionTypeSchema union = new UnionTypeSchema();

                union.setAnyOf(anyOf);

                return union;
            }
            else if (type.isTextual()) {
                String typeId = json.get("type").asText();
                Class<? extends JsonSchema> typeClass = typeFromId(typeId);

                return mapper.treeToValue(json, typeClass);
            }
            else {
                throw JsonMappingException.from(ctxt, "Expected type to be string or array but was " + type);
            }
        } else if (json.has("anyOf")) {
            return mapper.treeToValue(json, UnionTypeSchema.class);
        } else if (json.has("allOf")) {
            return mapper.treeToValue(json, IntersectionTypeSchema.class);
        } else if (json.has("oneOf")) {
            return mapper.treeToValue(json, OneOfSchema.class);
        } else if (json.has("not")) {
            return mapper.treeToValue(json, NotSchema.class);
        } else if (json.has("$ref")) {
            return mapper.treeToValue(json, ReferenceSchema.class);
        } else {
            return mapper.treeToValue(json, typeFromId("object"));
        }
    }

    public static Class<? extends JsonSchema> typeFromId(String id) {
        JsonFormatTypes stdType = JsonFormatTypes.forValue(id);

        if (stdType != null) {
            switch (stdType) {
                case ARRAY:
                    return (ArraySchema.class);
                case BOOLEAN:
                    return (BooleanSchema.class);
                case INTEGER:
                    return (IntegerSchema.class);
                case NULL:
                    return (NullSchema.class);
                case NUMBER:
                    return (NumberSchema.class);
                case OBJECT:
                    return (ObjectSchema.class);
                case STRING:
                    return (StringSchema.class);
                case ANY:
                default:
                    return (AnySchema.class);
            }
        }
        // Not a standard type; should use a custom sub-type impl
        throw new IllegalArgumentException("Can not resolve JsonSchema 'type' id of \"" + id
                                           + "\", not recognized as one of standard values: " + Arrays.asList(JsonFormatTypes.values()));
    }

    public static JsonSchema newInstanceFromId(String id) {
        JsonFormatTypes stdType = JsonFormatTypes.forValue(id);

        if (stdType != null) {
            switch (stdType) {
                case ARRAY:
                    return new ArraySchema();
                case BOOLEAN:
                    return new BooleanSchema();
                case INTEGER:
                    return new IntegerSchema();
                case NULL:
                    return new NullSchema();
                case NUMBER:
                    return new NumberSchema();
                case OBJECT:
                    return new ObjectSchema();
                case STRING:
                    return new StringSchema();
                case ANY:
                default:
                    return new AnySchema();
            }
        }

        // Not a standard type; should use a custom sub-type impl
        throw new IllegalArgumentException("Can not resolve JsonSchema 'type' id of \"" + id
                                           + "\", not recognized as one of standard values: " + Arrays.asList(JsonFormatTypes.values()));
    }
}
