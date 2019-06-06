package yang.tdd.arg;

public class ArgSchema {
	private static final String INTEGER_REX = "^(\\d)*$";
	private String argType;
	private String argFlag;
	private Object argValue;

	public ArgSchema(String schema) {
		parsSchema(schema);
	}

	private void parsSchema(String schema) {
		String[] schemaParameter = schema.split(",");
		if (schemaParameter.length == 3) {

			this.setArgFlag(schemaParameter[0].trim());
			this.setArgType(schemaParameter[1].trim());
			parsValue(schemaParameter[2].trim());
		}
	}

	public boolean parsValue(String value) {
		if (argType.equalsIgnoreCase("Boolean")) {
			return convertBoolean(value);
		}
		if (argType.equalsIgnoreCase("Integer")) {
			return convertInteger(value);
		}
		if (argType.equalsIgnoreCase("String")) {
			return convertString(value);
		}
		return false;
	}

	private boolean convertInteger(String value) {
		if (!value.matches(INTEGER_REX)) {
			return false;
		}
		if (value.isEmpty()) {
			argValue = Integer.valueOf(0);
		} else {
			argValue = Integer.valueOf(value);
		}
		return true;
	}

	private boolean convertString(String value) {
		argValue = value;
		return true;
	}

	private boolean convertBoolean(String value) {
		if (value.isEmpty()) {
			argValue = true;
		} else {
			argValue = Boolean.valueOf(value);
		}
		return true;
	}

	public String getArgType() {
		return argType;
	}

	public void setArgType(String argType) {
		this.argType = argType;
	}

	public String getArgFlag() {
		return argFlag;
	}

	public void setArgFlag(String argFlag) {
		this.argFlag = argFlag;
	}

	public Object getValue() {
		return argValue;
	}

	public void setValue(Object defaultValue) {
		this.argValue = defaultValue;
	}

}
