package yang.tdd.arg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArgsParserTest {
	
	@Test
	public void argSchemaParseTest(){
		String schema="l, Boolean, false";
		ArgSchema schemaL=new ArgSchema(schema);
		assertEquals("l", schemaL.getArgFlag());
		assertEquals("Boolean", schemaL.getArgType());
		assertEquals(false, (Boolean)schemaL.getValue());
	}
	
	@Test 
	public void oneBooleanArgTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="-l";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		argsProcessor.paseArgs(args);
		assertEquals(true, argsProcessor.quearyArg("l"));
	}
	@Test 
	public void booleanArgWitDefaultTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		argsProcessor.paseArgs(args);
		assertEquals(false, argsProcessor.quearyArg("l"));
	}
	@Test 
	public void integerArgWitDefaultTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		argsProcessor.paseArgs(args);
		assertEquals(0, argsProcessor.quearyArg("p"));
	}
	@Test 
	public void integerArgTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="-p 8080";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		argsProcessor.paseArgs(args);
		assertEquals(8080, argsProcessor.quearyArg("p"));
	}
	@Test 
	public void integerIllegalArgTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="-p abc";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		String tips=argsProcessor.paseArgs(args);
		assertEquals("Please put in correct arg and value!", tips);
	}
	@Test 
	public void stringArgTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="-d /usr/local";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		String tips=argsProcessor.paseArgs(args);
		assertEquals("/usr/local", (String)argsProcessor.quearyArg("d"));
	}
	@Test 
	public void normalArgTest() {
		String schema="l, Boolean, false;p, Integer, 0;d, String, ";
		String args="-l -p 8080 -d /usr/local";
		ArgsProcess argsProcessor=new ArgsProcess(schema);
		String tips=argsProcessor.paseArgs(args);
		assertEquals(true, argsProcessor.quearyArg("l"));
		assertEquals(8080, argsProcessor.quearyArg("p"));
		assertEquals("/usr/local", (String)argsProcessor.quearyArg("d"));
	}

}
