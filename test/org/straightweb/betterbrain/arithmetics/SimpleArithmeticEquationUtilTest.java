package org.straightweb.betterbrain.arithmetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleArithmeticEquationUtilTest {

	@Test
	public void testFormatEquation() {
		org.junit.Assert.assertTrue("1 + 1 = 2".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(1, 1, ArithmeticOperation.ADDITION), true)));
		org.junit.Assert.assertTrue("2 x 2 = 4".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(2, 2, ArithmeticOperation.MULTIPLICATION), true)));
		org.junit.Assert.assertTrue("10 - 20 = -10".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(10, 20, ArithmeticOperation.SUBTRACTION), true)));
		org.junit.Assert.assertTrue("2000 - 20 = 1980".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(2000, 20, ArithmeticOperation.SUBTRACTION), true)));
		
		org.junit.Assert.assertTrue("2 + 2 = ".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(2, 2, ArithmeticOperation.ADDITION), false)));
		org.junit.Assert.assertTrue("4 x 3 = ".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(4, 3, ArithmeticOperation.MULTIPLICATION), false)));
		org.junit.Assert.assertTrue("10 - 20 = ".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(10, 20, ArithmeticOperation.SUBTRACTION), false)));
		org.junit.Assert.assertTrue("2000 - 20 = ".equals(SimpleArithmeticEquationUtil.formatEquation(new SimpleArithmeticEquation(2000, 20, ArithmeticOperation.SUBTRACTION), false)));
	}
}
