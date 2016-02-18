package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public class IntersectionTypeSchema extends JsonSchema
{
	@JsonProperty
	protected JsonSchema[] allOf;

     @Override
     public boolean isIntersectionTypeSchema() {
          return true;
     }

     @Override
	public IntersectionTypeSchema asIntersectionTypeSchema() {
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
     
	public JsonSchema[] getAllOf() {
		return allOf;
	}

	public void setAllOf(JsonSchema[] allOf) {
	    if (allOf.length < 2) {
	        throw new IllegalArgumentException("Union Type Schemas must contain two or more Simple Type Schemas");
	    }
		this.allOf = allOf;
	}

     @Override
     public boolean equals(Object obj)
     {
         if (obj == this) return true;
         if (obj == null) return false;
         if (!(obj instanceof IntersectionTypeSchema)) return false;
         return _equals((IntersectionTypeSchema) obj);
     }

     protected boolean _equals(IntersectionTypeSchema that)
     {
         return arraysEqual(getAllOf(), that.getAllOf())
                 && super._equals(that);
     }
}