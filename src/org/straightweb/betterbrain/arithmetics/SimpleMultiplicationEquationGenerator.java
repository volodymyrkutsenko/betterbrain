package org.straightweb.betterbrain.arithmetics;

public class SimpleMultiplicationEquationGenerator  extends SimpleArithmeticEquationGenerator {
	
	private static final int UPPER_ARGUMENT_LIMIT = 10;

	@Override
	public SimpleArithmeticEquation generateEquation() {
		return new SimpleArithmeticEquation(generateArgument(), generateArgument(), ArithmeticOperation.MULTIPLICATION);
	}

	private int generateArgument() {
		return getRandom().nextInt(UPPER_ARGUMENT_LIMIT);
	}
}
