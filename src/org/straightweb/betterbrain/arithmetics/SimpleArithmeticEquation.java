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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstArgument;
		result = prime * result
				+ ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + secondArgument;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleArithmeticEquation other = (SimpleArithmeticEquation) obj;
		if (firstArgument != other.firstArgument)
			return false;
		if (operation != other.operation)
			return false;
		if (secondArgument != other.secondArgument)
			return false;
		return true;
	}

}
