package org.straightweb.betterbrain.arithmetics;

public class SimpleArithmeticEquationUtil {

	public static String formatEquation(SimpleArithmeticEquation equation, boolean includeAnswer) {
		StringBuilder result = new StringBuilder();
		result.append(equation.getFirstArgument());
		switch (equation.getOperation()) {
		case ADDITION:
			result.append(" + ");
			break;
		case SUBTRACTION:
			result.append(" - ");
			break;
		case MULTIPLICATION:
			result.append(" x ");
			break;
		default:
			result.append(" <undefined> ");
		}
		result.append(equation.getSecondArgument()).append(" = ");
		
		if (includeAnswer) {
			result.append(equation.getResult());
		}

		return result.toString();
	}
}
