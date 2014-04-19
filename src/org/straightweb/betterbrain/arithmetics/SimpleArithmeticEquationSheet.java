package org.straightweb.betterbrain.arithmetics;

import java.util.List;

public class SimpleArithmeticEquationSheet {

	private List<SimpleArithmeticEquation> equations;

	public SimpleArithmeticEquationSheet(List<SimpleArithmeticEquation> equations) {
		if (equations == null) {
			throw new IllegalArgumentException("The equations parameter cannot be null");
		}
		this.equations = equations;
	}

	public List<SimpleArithmeticEquation> getEquations() {
		return equations;
	}
	
}
