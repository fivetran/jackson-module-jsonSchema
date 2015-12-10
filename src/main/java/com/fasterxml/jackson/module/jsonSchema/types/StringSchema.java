package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import java.io.UncheckedIOException;

/**
 * This represents a {@link JsonSchema} as a String
 * @author jphelan
 *
 */
public class StringSchema extends NumberishSchema {

	/** this defines the maximum length of the string. */
	@JsonProperty
	private Integer maxLength;

	/** this defines the minimum length of the string. */
	@JsonProperty
	private Integer minLength;
	/**
	 * this provides a regular expression that a string instance MUST match in
	 * order to be valid. Regular expressions SHOULD follow the regular
	 * expression specification from ECMA 262/Perl 5
	 */
	@JsonProperty
	private String pattern;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String dateFormat;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String dateTimeFormat;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String decimalFormat;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String truePattern;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String falsePattern;

	@Override
	public StringSchema asStringSchema() {
		return this;
	}

	public Integer getMaxLength() {
	    return maxLength;
	}

	public Integer getMinLength() {
	    return minLength;
	}

	public String getPattern() {
	    return pattern;
	}

	@Override
	public JsonFormatTypes getType() {
	    return JsonFormatTypes.STRING;
	}

	@Override
	public boolean isStringSchema() {
	    return true;
	}

	public void setMaxLength(Integer maxLength) {
	    this.maxLength = maxLength;
	}

	public void setMinLength(Integer minLength) {
	    this.minLength = minLength;
	}

	public void setPattern(String pattern) {
	    this.pattern = pattern;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDateTimeFormat() {
		return dateTimeFormat;
	}

	public void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}

	public String getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(String decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	@Override
     public boolean equals(Object obj)
     {
         if (obj == this) return true;
         if (obj == null) return false;
         if (!(obj instanceof StringSchema)) return false;
         return _equals((StringSchema) obj);
     }

     protected boolean _equals(StringSchema that)
     {
         return equals(getMaxLength(), that.getMaxLength())
                 && equals(getMinLength(), that.getMinLength())
                 && equals(getPattern(), that.getPattern())
                 && super.equals(that);
     }

	public String getTruePattern() {
		return truePattern;
	}

	public void setTruePattern(String truePattern) {
		this.truePattern = truePattern;
	}

	public String getFalsePattern() {
		return falsePattern;
	}

	public void setFalsePattern(String falsePattern) {
		this.falsePattern = falsePattern;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new UncheckedIOException(e);
		}
	}
}
