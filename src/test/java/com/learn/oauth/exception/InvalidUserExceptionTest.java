/**
 * 
 */
package com.learn.oauth.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author vkalyana
 *
 */
@DisplayName("Invalid User Exception tests")
 class InvalidUserExceptionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	 static void setUpBeforeClass() throws Exception {
		System.out.println("Before all Tests Execution");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	 static void tearDownAfterClass() throws Exception {
		System.out.println("After all Tests Execution");
	}

	InvalidUserException exception;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	 void setUp() throws Exception {
		System.out.println("BeforeEach Test Execution");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	 void tearDown() throws Exception {
		System.out.println("AfterEach Test Execution");
	}

	@DisplayName("should have default title and message")
	@Test
	 void testDefaultException() {
		exception = new InvalidUserException();
		Assertions.assertEquals("Authentication Failed", exception.getTitle());
		Assertions.assertEquals("Not a valid credentials", exception.getMessage());
	}

	@DisplayName("Should expect given title and error message")
	@Test
	 void testGivenException() {
		exception = new InvalidUserException("Exception title", "Error message");
		Assertions.assertEquals("Exception title", exception.getTitle());
		Assertions.assertEquals("Error message", exception.getMessage());
	}
	
	@DisplayName("Should throw title cannot be null for null title parameter")
	@Test
	 void testTitleNullyfyException() {
		Throwable exception = Assertions.assertThrows(NullPointerException.class, () -> new InvalidUserException(null, ""));
		Assertions.assertEquals("Title cannot be null", exception.getMessage());
	}
	
	@DisplayName("Should throw message cannot be null for null message parameter")
	@Test
	 void testMessageNullyfyException() {
		Throwable exception = Assertions.assertThrows(NullPointerException.class, () -> new InvalidUserException("", null));
		Assertions.assertEquals("Error message cannot be null", exception.getMessage());
	}
	
}
