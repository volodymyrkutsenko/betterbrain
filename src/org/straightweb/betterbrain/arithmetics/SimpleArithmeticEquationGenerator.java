package org.straightweb.betterbrain.arithmetics;

import java.util.Random;

public abstract class SimpleArithmeticEquationGenerator {

	private Random rand = new Random();
	
	public abstract SimpleArithmeticEquation generateEquation();
	
	public Random getRandom() {
		return rand;
	}
}
