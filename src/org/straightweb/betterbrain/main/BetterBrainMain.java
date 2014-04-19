package org.straightweb.betterbrain.main;

import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquation;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheet;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheetGenerator;

public class BetterBrainMain {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("For usage follow such call pattern 'betterbrain.jar <numberOfEquations> <maxNumberOfDuplicatesOnSheet>'");
			return;
		}

		int numberOfEquationsToGenerate = Integer.valueOf(args[0]);
		int duplicatesMaxQuantity = Integer.valueOf(args[1]);

		SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(numberOfEquationsToGenerate, duplicatesMaxQuantity);
		
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			System.out.println(equation);
		}
		
	}

}
