package yang.tdd.fizzbuzz;

public class FizzBuzz {

	public static String transfer(int i) {
		String result="";		
		if(i%3==0)result="Fizz";
		if(i%5==0)result+= "Buzz";
		return result.isEmpty()?result+i:result;
	}

}
