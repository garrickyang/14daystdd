package yang.tdd.fizzbuzz;

import static org.junit.Assert.*;

import org.junit.Test;

import yang.tdd.fizzbuzz.FizzBuzz;

public class FizzBuzzTest {

	@Test
	public void test() {
		assertEquals("1", FizzBuzz.transfer(1));
		assertEquals("Fizz", FizzBuzz.transfer(3));
		assertEquals("Buzz", FizzBuzz.transfer(5));
		assertEquals("FizzBuzz", FizzBuzz.transfer(15));
	}

}
