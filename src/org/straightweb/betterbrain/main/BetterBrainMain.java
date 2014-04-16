package org.straightweb.betterbrain.main;

import org.straightweb.betterbrain.arithmetics.SimpleAdditionEquationGenerator;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationGenerator;
import org.straightweb.betterbrain.arithmetics.SimpleMultiplicationEquationGenerator;
import org.straightweb.betterbrain.arithmetics.SimpleSubtractionEquationGenerator;

public class BetterBrainMain {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("For usage follow such call pattern 'betterbrain.jar <equationType (A/S/M)> <numberOfEquations>'");
			return;
		}

		SimpleArithmeticEquationGenerator equationGenerator;
		String equationGeneratorType = args[0];
		if ("A".equalsIgnoreCase(equationGeneratorType)) {
			equationGenerator = new SimpleAdditionEquationGenerator();
		} else if ("S".equalsIgnoreCase(equationGeneratorType)) {
			equationGenerator = new SimpleSubtractionEquationGenerator();
		} else if ("M".equalsIgnoreCase(equationGeneratorType)) {
			equationGenerator = new SimpleMultiplicationEquationGenerator();
		} else {
			System.out.println("Generator type can only be A, S or M");
			return;
		}

		int numberOfEquationsToGenerate = Integer.valueOf(args[1]);

		for (int i = 0; i < numberOfEquationsToGenerate; i++) {
			System.out.println(equationGenerator.generateEquation());
		}

	}

}
