package org.straightweb.betterbrain.arithmetics;

public class SimpleAdditionEquationGenerator extends SimpleArithmeticEquationGenerator {

	private static final int UPPER_ARGUMENT_LIMIT = 10;
	
	@Override
	public SimpleArithmeticEquation generateEquation() {
		return new SimpleArithmeticEquation(generateArgument(), generateArgument(), ArithmeticOperation.ADDITION);
	}
	
	private int generateArgument() {
		return getRandom().nextInt(UPPER_ARGUMENT_LIMIT);
	}

}
