package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.util.List;

public abstract class NumberishSchema extends ValueTypeSchema {
    /**
     * This attribute indicates if the value of the instance (if the
     instance is a number) can not equal the number defined by the
     "maximum" attribute.
     */
    @JsonProperty
    private Boolean exclusiveMaximum;

    /**
     * This attribute indicates if the value of the instance (if the
     instance is a number) can not equal the number defined by the
     "minimum" attribute.
     */
    @JsonProperty
    private Boolean exclusiveMinimum;

    /**This attribute defines the maximum value of the instance property*/
    @JsonProperty
    private BigDecimal maximum = null;

    /**This attribute defines the minimum value of the instance property*/
    @JsonProperty
    private BigDecimal minimum = null;

    /** The value of the instance needs to be a multiple of this attribute */
    @JsonProperty
    private BigDecimal multipleOf = null;

    public Boolean getExclusiveMaximum() {
        return exclusiveMaximum;
    }

    public Boolean getExclusiveMinimum() {
        return exclusiveMinimum;
    }

    public BigDecimal getMaximum() {
        return maximum;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public BigDecimal getMultipleOf() {
        return multipleOf;
    }

    public void setExclusiveMaximum(Boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public void setExclusiveMinimum(Boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public void setMaximum(BigDecimal maximum) {
        this.maximum = maximum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public void setMultipleOf(BigDecimal multipleOf) {
        this.multipleOf = multipleOf;
    }

    private static final ObjectMapper CONVERTER = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }

    @Override
    public NumberSchema asNumberSchema() {
        NumberSchema number = CONVERTER.convertValue(this, NumberSchema.class);

        return number;
    }
}
