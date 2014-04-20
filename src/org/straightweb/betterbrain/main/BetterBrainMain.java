package org.straightweb.betterbrain.main;

import org.straightweb.betterbrain.arithmetics.EquationSheetGenerateRequest;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquation;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheet;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheetGenerator;

public class BetterBrainMain {

	public static void main(String[] args) {
		if (args.length > 0 && "help".equalsIgnoreCase(args[0])) {
			System.out.println("For usage follow such call pattern 'betterbrain.jar [numberOfEquations] [maxNumberOfDuplicatesOnSheet] [maxZeroArgumentedEquationsQuantity] [maxZeroResultEquationsQuantity]'");
			return;
		}

		EquationSheetGenerateRequest sheetGenerateRequest = new EquationSheetGenerateRequest();
		
		if (args.length > 0) {
			int numberOfEquationsToGenerate = Integer.valueOf(args[0]);
			sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate);
			if (args.length > 1) {
				int duplicatesMaxQuantity = Integer.valueOf(args[1]);
				sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate, duplicatesMaxQuantity);
				if (args.length > 2) {
					int zeroArgumentedEquationsMaxQuantity = Integer.valueOf(args[2]);
					sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate, duplicatesMaxQuantity, zeroArgumentedEquationsMaxQuantity);
					if (args.length > 3) {
						int zeroResultEquationsMaxQuantity = Integer.valueOf(args[3]);
						sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate, duplicatesMaxQuantity, zeroArgumentedEquationsMaxQuantity, zeroResultEquationsMaxQuantity);
					}
				}
			}
		}

		SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(sheetGenerateRequest);
		
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			System.out.println(equation);
		}
		
	}

}
