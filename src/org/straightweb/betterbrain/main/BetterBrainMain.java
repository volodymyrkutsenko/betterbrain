package org.straightweb.betterbrain.main;

import org.straightweb.betterbrain.arithmetics.EquationSheetGenerateRequest;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquation;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheet;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheetGenerator;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationUtil;

public class BetterBrainMain {

	public static void main(String[] args) {
		if (args.length > 0 && "help".equalsIgnoreCase(args[0])) {
			System.out.println("For usage follow such call pattern 'betterbrain.jar [-includeAnswers] [numberOfEquations] [maxNumberOfDuplicatesOnSheet] [maxZeroArgumentedEquationsQuantity] [maxZeroResultEquationsQuantity]'");
			return;
		}

		int startArgIndexForFurtherOptions = 0;
		boolean includeAnswers = false;
		if (args.length > 0 && "-includeAnswers".equalsIgnoreCase(args[0])) {
			includeAnswers = true;
			startArgIndexForFurtherOptions = 1;
		}
		
		EquationSheetGenerateRequest sheetGenerateRequest = new EquationSheetGenerateRequest();
		
		if (args.length > 0 + startArgIndexForFurtherOptions) {
			int numberOfEquationsToGenerate = Integer.valueOf(args[0 + startArgIndexForFurtherOptions]);
			sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate);
			if (args.length > 1 + startArgIndexForFurtherOptions) {
				int duplicatesMaxQuantity = Integer.valueOf(args[1 + startArgIndexForFurtherOptions]);
				sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate, duplicatesMaxQuantity);
				if (args.length > 2 + startArgIndexForFurtherOptions) {
					int zeroArgumentedEquationsMaxQuantity = Integer.valueOf(args[2 + startArgIndexForFurtherOptions]);
					sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate, duplicatesMaxQuantity, zeroArgumentedEquationsMaxQuantity);
					if (args.length > 3 + startArgIndexForFurtherOptions) {
						int zeroResultEquationsMaxQuantity = Integer.valueOf(args[3 + startArgIndexForFurtherOptions]);
						sheetGenerateRequest = new EquationSheetGenerateRequest(numberOfEquationsToGenerate, duplicatesMaxQuantity, zeroArgumentedEquationsMaxQuantity, zeroResultEquationsMaxQuantity);
					}
				}
			}
		}

		SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(sheetGenerateRequest);
		
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			System.out.println(SimpleArithmeticEquationUtil.formatEquation(equation, includeAnswers));
		}
		
	}

}
