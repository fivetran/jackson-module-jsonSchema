package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public class OneOfSchema extends JsonSchema
{
	@JsonProperty
	protected JsonSchema[] oneOf;

     @Override
     public boolean isOneOfSchema() {
          return true;
     }

     @Override
	public OneOfSchema asOneOfSchema() {
		return this;
	}

     // Important: This is the Type Id, as defined by base class `JsonSchema`
     @Override
     public JsonFormatTypes getType() {
         // 29-Dec-2015, tatu: As per [module-jsonSchema#90], can not return null.
         //  ... but, alas, there is no real suitable value to return. Just can not
         //  be null; but if not null, will result in wrong deserialization.
//         return JsonFormatTypes.UNION;
         return null;
     }
     
	public JsonSchema[] getOneOf() {
		return oneOf;
	}

	public void setOneOf(JsonSchema[] oneOf) {
	    if (oneOf.length < 2) {
	        throw new IllegalArgumentException("Union Type Schemas must contain two or more Simple Type Schemas");
	    }
		this.oneOf = oneOf;
	}

     @Override
     public boolean equals(Object obj)
     {
         if (obj == this) return true;
         if (obj == null) return false;
         if (!(obj instanceof OneOfSchema)) return false;
         return _equals((OneOfSchema) obj);
     }

     protected boolean _equals(OneOfSchema that)
     {
         return arraysEqual(getOneOf(), that.getOneOf())
                 && super._equals(that);
     }
}