package yang.tdd.arg;

import java.util.HashMap;
import java.util.Map;

public class ArgsProcess {

	private static final String PARSE_SUCCESS_MESSAGE = "";
	private static final String PARSE_ERROR_MASSEGE = "Please put in correct arg and value!";
	private Map<String, ArgSchema> argSchemas;
	private Map<String, ArgSchema> argValues;

	public ArgsProcess(String schema) {
		argSchemas = new HashMap<String, ArgSchema>();
		argValues = new HashMap<String, ArgSchema>();
		parseSchema(schema);
	}

	private void parseSchema(String schemaInput) {
		String[] schemas = schemaInput.split(";");
		ArgSchema argSchema;
		for (String schema : schemas) {
			argSchema = new ArgSchema(schema);
			argSchemas.put(argSchema.getArgFlag(), argSchema);
		}
	}

	public Object quearyArg(String argFlag) {
		if (argValues.containsKey(argFlag)) {
			return argValues.get(argFlag).getValue();
		}
		return null;
	}

	public String paseArgs(String args) {
		argValues.putAll(argSchemas);
		for (String aArgValue : args.split("-")) {
			if (aArgValue.isEmpty()) {
				continue;
			}
			if (!isValideFlag(aArgValue)) {
				return PARSE_ERROR_MASSEGE;
			}
			if (!parseOneArg(aArgValue))
				return PARSE_ERROR_MASSEGE;
		}
		return PARSE_SUCCESS_MESSAGE;
	}

	private boolean parseOneArg(String aArgValue) {
		ArgSchema argSchema;
		argSchema = argValues.get(aArgValue.substring(0, 1));
		return argSchema.parsValue(aArgValue.substring(1).trim());
	}

	private boolean isValideFlag(String aArgValue) {

		return argValues.containsKey(aArgValue.substring(0, 1));
	}

}
