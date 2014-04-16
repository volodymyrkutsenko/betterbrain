package org.straightweb.betterbrain.arithmetics;

public class SimpleArithmeticEquation {

	private int firstArgument;
	private int secondArgument;
	private ArithmeticOperation operation;
	
	public SimpleArithmeticEquation(int firstArgument, int secondArgument,
			ArithmeticOperation operation) {
		this.firstArgument = firstArgument;
		this.secondArgument = secondArgument;
		if (operation == null) {
			throw new IllegalArgumentException("The operation parameter cannot be null");
		}
		this.operation = operation;
	}

	public int getFirstArgument() {
		return firstArgument;
	}

	public int getSecondArgument() {
		return secondArgument;
	}

	public ArithmeticOperation getOperation() {
		return operation;
	}

	public int getResult() {
		switch (getOperation()) {
		case ADDITION:
			return getFirstArgument() + getSecondArgument();
		case SUBTRACTION:
			return getFirstArgument() - getSecondArgument();
		case MULTIPLICATION:
			return getFirstArgument() * getSecondArgument();
		default:
			return 0;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getFirstArgument());
		switch (getOperation()) {
		case ADDITION:
			result.append(" + ");
			break;
		case SUBTRACTION:
			result.append(" - ");
			break;
		case MULTIPLICATION:
			result.append(" * ");
			break;
		default:
			result.append(" <undefined> ");
		}
		result.append(getSecondArgument()).append(" = ").append(getResult());

		return result.toString();
	}

}
