package com.fasterxml.jackson.module.jsonSchema.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

/**
 * This class encapsulates the functionality of {@link JsonSchema} simple types
 * @author jphelan
 */
public abstract class SimpleTypeSchema extends JsonSchema
{
	/**
	 * This attribute defines the default value of the instance when the
	 * instance is undefined.
	 */
	protected String defaultdefault;

	/**
	 * This attribute is a string that provides a short description of the
	 * instance property.
	 */
	protected String title;

    /**
     * Iglu-style self-ref
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected Self self = new Self();

    public static class Self {
        public String vendor, name, format, version;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Self self = (Self) o;

            if (vendor != null ? !vendor.equals(self.vendor) : self.vendor != null) return false;
            if (name != null ? !name.equals(self.name) : self.name != null) return false;
            if (format != null ? !format.equals(self.format) : self.format != null) return false;
            return !(version != null ? !version.equals(self.version) : self.version != null);

        }

        @Override
        public int hashCode() {
            int result = vendor != null ? vendor.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (format != null ? format.hashCode() : 0);
            result = 31 * result + (version != null ? version.hashCode() : 0);
            return result;
        }
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    /**
	 * This attribute is a URI that defines what the instance's URI MUST start with in order to validate.
	 */
	protected String pathStart;

	/**
	 * This attribute is a string that provides a links related to description of the
	 * instance property.
	 */
	protected LinkDescriptionObject[] links;

	@Override
	public SimpleTypeSchema asSimpleTypeSchema() {
		return this;
	}

	public String getDefault() {
		return defaultdefault;
	}

	public String getTitle() {
		return title;
	}

    public String getPathStart() {
        return pathStart;
    }

    public LinkDescriptionObject[] getLinks() {
        return links;
    }

    public void setLinks(LinkDescriptionObject[] links) {
        this.links = links;
    }

	@Override
	public boolean isSimpleTypeSchema() {
		return true;
	}

	public void setDefault(String defaultdefault) {
		this.defaultdefault = defaultdefault;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public void setPathStart(String pathStart) {
        this.pathStart = pathStart;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) return true;
        if (obj == null) return false;
        if (!(obj instanceof SimpleTypeSchema)) return false;
        return _equals((SimpleTypeSchema) obj);
    }

    protected boolean _equals(SimpleTypeSchema that)
    {
        return equals(getDefault(), that.getDefault())
                && equals(getTitle(), that.getTitle())
                && equals(getPathStart(), that.getPathStart())
                && arraysEqual(getLinks(), that.getLinks())
                && super._equals(that);
    }
}
