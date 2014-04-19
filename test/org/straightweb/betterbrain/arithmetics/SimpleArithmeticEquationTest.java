package org.straightweb.betterbrain.arithmetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleArithmeticEquationTest {

	@Test
	public void testAdditionResultCalculation() {
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.ADDITION).getResult(), 2);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(2, 2, ArithmeticOperation.ADDITION).getResult(), 4);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(0, 0, ArithmeticOperation.ADDITION).getResult(), 0);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(5, -10, ArithmeticOperation.ADDITION).getResult(), -5);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(Integer.MAX_VALUE, 1, ArithmeticOperation.ADDITION).getResult(), Integer.MIN_VALUE);
	}
	
	@Test
	public void testSubtractionResultCalculation() {
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.SUBTRACTION).getResult(), 0);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(2, 2, ArithmeticOperation.SUBTRACTION).getResult(), 0);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(0, 0, ArithmeticOperation.SUBTRACTION).getResult(), 0);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(5, -10, ArithmeticOperation.SUBTRACTION).getResult(), 15);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(Integer.MIN_VALUE, 1, ArithmeticOperation.SUBTRACTION).getResult(), Integer.MAX_VALUE);
	}
	
	@Test
	public void testMultiplicationResultCalculation() {
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.MULTIPLICATION).getResult(), 1);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(2, 2, ArithmeticOperation.MULTIPLICATION).getResult(), 4);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(0, 0, ArithmeticOperation.MULTIPLICATION).getResult(), 0);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(5, -10, ArithmeticOperation.MULTIPLICATION).getResult(), -50);
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(Integer.MAX_VALUE - 20, 4, ArithmeticOperation.MULTIPLICATION).getResult(), -84); // this is going to overflow
		org.junit.Assert.assertEquals(new SimpleArithmeticEquation(Integer.MIN_VALUE, -1, ArithmeticOperation.MULTIPLICATION).getResult(), Integer.MIN_VALUE);
	}
	
	@Test
	public void testToString() {
		org.junit.Assert.assertTrue("1 + 1 = 2".equals(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.ADDITION).toString()));
		org.junit.Assert.assertTrue("2 * 2 = 4".equals(new SimpleArithmeticEquation(2, 2, ArithmeticOperation.MULTIPLICATION).toString()));
		org.junit.Assert.assertTrue("10 - 20 = -10".equals(new SimpleArithmeticEquation(10, 20, ArithmeticOperation.SUBTRACTION).toString()));
		org.junit.Assert.assertTrue("2000 - 20 = 1980".equals(new SimpleArithmeticEquation(2000, 20, ArithmeticOperation.SUBTRACTION).toString()));
	}
	
	@Test
	public void testNullForOperationType() {
		try {
			new SimpleArithmeticEquation(1, 1, null);
			org.junit.Assert.fail("SimpleArithmeticEquation should have thrown an exception b/c of null as the operation type!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testEquality() {
		org.junit.Assert.assertTrue(new SimpleArithmeticEquation(1, 2, ArithmeticOperation.MULTIPLICATION).equals(new SimpleArithmeticEquation(1, 2, ArithmeticOperation.MULTIPLICATION)));
		org.junit.Assert.assertFalse(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.MULTIPLICATION).equals(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.SUBTRACTION)));
		org.junit.Assert.assertFalse(new SimpleArithmeticEquation(1, 2, ArithmeticOperation.MULTIPLICATION).equals(new SimpleArithmeticEquation(2, 1, ArithmeticOperation.MULTIPLICATION)));
	}
	
}
