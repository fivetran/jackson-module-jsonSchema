package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import java.math.BigDecimal;

/**
 * This class represents a {@link JsonSchema} as a number type
 * @author jphelan
 */
public class NumberSchema extends NumberishSchema {
	
	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.jsonSchema.types.JsonSchema#getType()
	 */
	@Override
	public JsonFormatTypes getType()
	{
	    return JsonFormatTypes.NUMBER;
	}
	
	@Override
	public boolean isNumberSchema() { return true; }

     @Override
     public boolean equals(Object obj)
     {
         if (obj == this) return true;
         if (obj == null) return false;
         if (!(obj instanceof NumberSchema)) return false;
         return _equals((NumberSchema) obj);
     }

     protected boolean _equals(NumberSchema that)
     {
         return equals(getExclusiveMaximum(), that.getExclusiveMaximum()) &&
                 equals(getExclusiveMinimum(), that.getExclusiveMinimum()) &&
                 equals(getMaximum(), that.getMaximum()) &&
                 equals(getMinimum(), that.getMinimum()) &&
                 equals(getMultipleOf(), that.getMultipleOf()) &&
                 super._equals(that);
     }
}
