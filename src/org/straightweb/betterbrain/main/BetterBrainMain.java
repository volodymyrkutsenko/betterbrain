package org.straightweb.betterbrain.main;

import java.util.List;

import org.straightweb.betterbrain.arithmetics.EquationSheetGenerateRequest;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquation;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheet;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheetGenerator;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationUtil;
import org.straightweb.betterbrain.memorization.MemorizationSetGenerateRequest;
import org.straightweb.betterbrain.memorization.MemorizationSetGenerator;

public class BetterBrainMain {

	public static void main(String[] args) {
		if (args.length > 0 && "help".equalsIgnoreCase(args[0])) {
			System.out.println("For usage follow such call pattern 'betterbrain.jar [-includeAnswers] [numberOfEquations] [maxNumberOfDuplicatesOnSheet] [maxZeroArgumentedEquationsQuantity] [maxZeroResultEquationsQuantity]'");
			return;
		}

		boolean needPrintEquations = !keyIsPresent(args, "--noequations");
		if (needPrintEquations) {
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
    		
    		printEquations(sheet.getEquations(), 0, sheet.getEquations().size() / 2, includeAnswers);
    		System.out.println();
    		printEquations(sheet.getEquations(), sheet.getEquations().size() / 2, sheet.getEquations().size(), includeAnswers);
		}
		
        if (keyIsPresent(args, "--wordset")) {
            if (needPrintEquations) {
                System.out.println();
                System.out.println();
            }
            printMemorizationSet(new MemorizationSetGenerator().generateSet(new MemorizationSetGenerateRequest()));
        }
	}
	
	private static boolean keyIsPresent(String[] args, String key) {
	    for (String arg : args) {
	        if (key.equalsIgnoreCase(arg)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private static void printEquations(List<SimpleArithmeticEquation> equations, int startIndex, int endIndex, boolean includeAnswers) {
		int size = endIndex - startIndex;
		int equationsLeftUnprinted = size % 3; 
		
		for (int i = startIndex; i < startIndex + size - equationsLeftUnprinted; i = i + 3) {
			System.out.println(String.format("%9s%18s%18s", 
					SimpleArithmeticEquationUtil.formatEquation(equations.get(i), includeAnswers), 
					SimpleArithmeticEquationUtil.formatEquation(equations.get(i + 1), includeAnswers), 
					SimpleArithmeticEquationUtil.formatEquation(equations.get(i + 2), includeAnswers)));
		}
		
		switch (equationsLeftUnprinted) {
		case 1:
			System.out.println(String.format("%9s", 
					SimpleArithmeticEquationUtil.formatEquation(equations.get(startIndex + size - equationsLeftUnprinted), includeAnswers)));
			break;
		case 2:
			System.out.println(String.format("%9s%18s", 
					SimpleArithmeticEquationUtil.formatEquation(equations.get(startIndex + size - equationsLeftUnprinted), includeAnswers), 
					SimpleArithmeticEquationUtil.formatEquation(equations.get(startIndex + size - equationsLeftUnprinted + 1), includeAnswers)));
			break;
		}
	}

	private static void printMemorizationSet(List<String> wordSet) {
	    for (int i = 0; i < 6; i++) {
	        int startingLineWordIdex = i * 5; 
	        System.out.format("%-16s%-16s%-16s%-16s%-16s", wordSet.get(startingLineWordIdex), wordSet.get(startingLineWordIdex + 1), wordSet.get(startingLineWordIdex + 2), wordSet.get(startingLineWordIdex + 3), wordSet.get(startingLineWordIdex + 4));
	        System.out.print("\n");
	    }
	}
}
