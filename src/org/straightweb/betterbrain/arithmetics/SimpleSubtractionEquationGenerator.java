package org.straightweb.betterbrain.arithmetics;

public class SimpleSubtractionEquationGenerator extends SimpleArithmeticEquationGenerator {

	private static final int FIRST_ARGUMENT_UPPER_LIMIT = 19;
	private static final int SECOND_ARGUMENT_UPPER_LIMIT = 10;
	
	@Override
	public SimpleArithmeticEquation generateEquation() {
		int firstArgument = generateFirstArgument();
		int secondArgument = generateSecondArgument(firstArgument);
		return new SimpleArithmeticEquation(firstArgument, secondArgument, ArithmeticOperation.SUBTRACTION);
	}
	
	private int generateFirstArgument() {
		return getRandom().nextInt(FIRST_ARGUMENT_UPPER_LIMIT);
	}
	
	private int generateSecondArgument(int firstArgument) {
		int secondArgument;
		do {
			secondArgument = getRandom().nextInt(SECOND_ARGUMENT_UPPER_LIMIT);
		} while (secondArgument > firstArgument);
		return secondArgument;
	}

}
