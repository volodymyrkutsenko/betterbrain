package org.straightweb.betterbrain.arithmetics;


public class SimpleArithmeticEquation {
	private int firstArgument;
	private int secondArgument;
	private int result;
	private ArithmeticOperation operation;
	
	private String toStringValue;
	
	public SimpleArithmeticEquation(int firstArgument, int secondArgument,
			ArithmeticOperation operation) {
		this.firstArgument = firstArgument;
		this.secondArgument = secondArgument;
		if (operation == null) {
			throw new IllegalArgumentException("The operation parameter cannot be null");
		}
		this.operation = operation;
		
		calculateResult();
		calculateToStringValue();
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
		return result;
	}
	
	public boolean isZeroArgumented() {
		return getFirstArgument() == 0 || getSecondArgument() == 0;
	}
	
	public boolean isZeroResult() {
		return getResult() == 0;
	}
	
	private void calculateResult() {
		switch (getOperation()) {
		case ADDITION:
			result = getFirstArgument() + getSecondArgument();
			break;
		case SUBTRACTION:
			result = getFirstArgument() - getSecondArgument();
			break;
		case MULTIPLICATION:
			result = getFirstArgument() * getSecondArgument();
			break;
		}
	}
	
	private void calculateToStringValue() {
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

		toStringValue = result.toString();
	}
	
	@Override
	public String toString() {
		return toStringValue;
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
