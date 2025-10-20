package com.UnitTest.UnitTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UnitTestApplicationTests {

	@Test
	void testAddition() {
		//logic
		int result = 2+3; //Logic that needs to be tested
		assertEquals(5, result); //positive test where you are checking wether the test is doing what it
		//is excpected to do
		//assertEquals(expected, actual);

		assertNotEquals(6, result);
	}

	@Test
	@DisplayName("Testing String length function test")
	void testStringLength(){
		String name = "Junit";
		assertEquals(5, name.length());
		assertTrue(name.startsWith("J"));
		assertFalse(3 > 5);
		assertNotNull("hello");

		//when you want to do multiple checks on a single variable
		assertAll("name checks", () -> assertEquals(5, name.length()),
				() -> assertTrue(name.contains("u")),
				() -> assertFalse(name.isEmpty())
				);
	}

	@Disabled("Fix later")  //To disable the test
	@Test
	void testDivisionByZero() {
		//if there is any arithmatic issue then throw the arithetic excetption
		//similary if during getProductById if the id is invalid then this assertThrows should throw the
		//product not found exception
		assertThrows(ArithmeticException.class, ()-> {int x = 1/0;});
	}

	@ParameterizedTest
	@CsvSource({"2,3,4", "10,5,15"})
	void testAddition(int a ,int b, int sum){
		assertEquals(sum, a + b);
	}

}
