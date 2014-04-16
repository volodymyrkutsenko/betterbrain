package org.straightweb.betterbrain.arithmetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleMultiplicationEquationGeneratorTest {

	private static final int TEST_SAMPLES_QUANTITY = 100;
	
	private static final int UPPER_ARGUMENT_LIMIT = 10;
	
	@Test
	public void testArgumentsUpperLimit() {
		SimpleArithmeticEquationGenerator additionEquationGenerator = new SimpleMultiplicationEquationGenerator();
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquation additionEquation = additionEquationGenerator.generateEquation();
			org.junit.Assert.assertNotNull(additionEquation);
			org.junit.Assert.assertTrue(additionEquation.getFirstArgument() < UPPER_ARGUMENT_LIMIT);
			org.junit.Assert.assertTrue(additionEquation.getSecondArgument() < UPPER_ARGUMENT_LIMIT);
		}
	}
	
	@Test
	public void testOperationType() {
		SimpleArithmeticEquationGenerator additionEquationGenerator = new SimpleMultiplicationEquationGenerator();
		org.junit.Assert.assertTrue(additionEquationGenerator.generateEquation().getOperation() == ArithmeticOperation.MULTIPLICATION);
	}
}
