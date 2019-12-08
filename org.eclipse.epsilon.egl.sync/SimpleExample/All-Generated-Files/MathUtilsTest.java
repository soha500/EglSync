package org.eclipse.epsilon.egl.sync;

import static org.junit.Assert.*;

import java.lang.annotation.Repeatable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.RepeatedTest;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) // we do not need static if we use this
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)

public class MathUtilsTest {

	@Test
	public void test() {
		MathUtils mathUtils = new MathUtils();
		int expected = 2;
		int actual = mathUtils.add(1, 1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMultiply() {
		MathUtils mathUtils = new MathUtils();
		int expected = 4;
		int actual = mathUtils.multiply(2, 2);
		assertEquals(expected, actual);
	}


}

