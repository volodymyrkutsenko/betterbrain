package org.straightweb.betterbrain.arithmetics;

public class SimpleSubtractionEquationGenerator extends SimpleArithmeticEquationGenerator {

	private static final int FIRST_ARGUMENT_UPPER_LIMIT = 18;
	private static final int SECOND_ARGUMENT_UPPER_LIMIT = 9;
	
	@Override
	public SimpleArithmeticEquation generateEquation() {
		int firstArgument = generateFirstArgument();
		int secondArgument = generateSecondArgument(firstArgument);
		return new SimpleArithmeticEquation(firstArgument, secondArgument, ArithmeticOperation.SUBTRACTION);
	}
	
	private int generateFirstArgument() {
		return getRandom().nextInt(FIRST_ARGUMENT_UPPER_LIMIT + 1);
	}
	
	private int generateSecondArgument(int firstArgument) {
		int secondArgumentLowerLimit = firstArgument - SECOND_ARGUMENT_UPPER_LIMIT;
		if (secondArgumentLowerLimit < 0) {
			secondArgumentLowerLimit = 0;
		}
		int limitByFirstArgument = firstArgument + 1;
		int secondArgument = getRandom().nextInt(limitByFirstArgument - secondArgumentLowerLimit < SECOND_ARGUMENT_UPPER_LIMIT + 1 - secondArgumentLowerLimit ? limitByFirstArgument - secondArgumentLowerLimit : SECOND_ARGUMENT_UPPER_LIMIT + 1 - secondArgumentLowerLimit) + secondArgumentLowerLimit;
		return secondArgument;
	}

}
