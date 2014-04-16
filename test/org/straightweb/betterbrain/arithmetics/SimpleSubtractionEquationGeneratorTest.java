package org.straightweb.betterbrain.arithmetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleSubtractionEquationGeneratorTest {

	private static final int TEST_SAMPLES_QUANTITY = 100;
	
	private static final int FIRST_ARGUMENT_UPPER_LIMIT = 19;
	private static final int SECOND_ARGUMENT_UPPER_LIMIT = 10;
	
	private static final int RESULT_LOWER_LIMIT = 0;
	
	@Test
	public void testArgumentsUpperLimit() {
		SimpleArithmeticEquationGenerator subtractionEquationGenerator = new SimpleSubtractionEquationGenerator();
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquation additionEquation = subtractionEquationGenerator.generateEquation();
			org.junit.Assert.assertNotNull(additionEquation);
			org.junit.Assert.assertTrue(additionEquation.getFirstArgument() < FIRST_ARGUMENT_UPPER_LIMIT);
			org.junit.Assert.assertTrue(additionEquation.getSecondArgument() < SECOND_ARGUMENT_UPPER_LIMIT);
		}
	}
	
	@Test
	public void testResultsLowerLimit() {
		SimpleArithmeticEquationGenerator subtractionEquationGenerator = new SimpleSubtractionEquationGenerator();
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquation additionEquation = subtractionEquationGenerator.generateEquation();
			org.junit.Assert.assertNotNull(additionEquation);
			org.junit.Assert.assertTrue(additionEquation.getResult() >= RESULT_LOWER_LIMIT);
		}
	}
	
	@Test
	public void testOperationType() {
		SimpleArithmeticEquationGenerator subtractionEquationGenerator = new SimpleSubtractionEquationGenerator();
		org.junit.Assert.assertTrue(subtractionEquationGenerator.generateEquation().getOperation() == ArithmeticOperation.SUBTRACTION);
	}
}
