package org.straightweb.betterbrain.main;

import java.math.BigInteger;
import java.util.List;

import org.straightweb.betterbrain.arithmetics.EquationSheetGenerateRequest;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquation;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheet;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationSheetGenerator;
import org.straightweb.betterbrain.arithmetics.SimpleArithmeticEquationUtil;
import org.straightweb.betterbrain.generating.DocxFileGenerator;
import org.straightweb.betterbrain.generating.DocxFileGeneratorConfiguration;
import org.straightweb.betterbrain.memorization.MemorizationSetGenerateRequest;
import org.straightweb.betterbrain.memorization.MemorizationSetGenerator;

public class BetterBrainMain {

	private static final int DEFAULT_EQUATION_SHEETS_NUMBER = 30;
	private static final String EQUATION_SHEET_DOCX_FILENAME = String.valueOf(DEFAULT_EQUATION_SHEETS_NUMBER) + "_equation_sheet.docx";
	private static final BigInteger DEFAULT_EQUATIONS_DOCX_FILE_LINE_SIZE = BigInteger.valueOf(320);
	private static final int DEFAULT_EQUATIONS_DOCX_FILE_FONT_SIZE = 24;

	public static void main(String[] args) {
		if (args.length > 0 && "help".equalsIgnoreCase(args[0])) {
			System.out.println("For usage follow such call pattern 'betterbrain.jar [-includeAnswers] [numberOfEquations] [maxNumberOfDuplicatesOnSheet] [maxZeroArgumentedEquationsQuantity] [maxZeroResultEquationsQuantity]'");
			return;
		}

		boolean needPrintEquations = !isKeyPresent(args, "--noequations");
		if (needPrintEquations) {
    		int startArgIndexForFurtherOptions = 0;
    		boolean includeAnswers = false;
    		if (args.length > 0 && "-includeAnswers".equalsIgnoreCase(args[0])) {
    			includeAnswers = true;
    			startArgIndexForFurtherOptions = 1;
    		}

			StringBuilder equationsBuilder = new StringBuilder();
			for (int i = 0; i < DEFAULT_EQUATION_SHEETS_NUMBER; i++) {
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

				appendEquations(sheet.getEquations(), 0, sheet.getEquations().size() / 2, includeAnswers, equationsBuilder);
				equationsBuilder.append('\n');
				appendEquations(sheet.getEquations(), sheet.getEquations().size() / 2, sheet.getEquations().size(), includeAnswers, equationsBuilder);
				equationsBuilder.append('\n');
			}

			writeDocxEquationsFile(equationsBuilder.toString());
		}
		
        if (isKeyPresent(args, "--wordset")) {
            if (needPrintEquations) {
                System.out.println();
                System.out.println();
            }
            printMemorizationSet(new MemorizationSetGenerator().generateSet(new MemorizationSetGenerateRequest()));
        }
	}
	
	private static boolean isKeyPresent(String[] args, String key) {
	    for (String arg : args) {
	        if (key.equalsIgnoreCase(arg)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private static void appendEquations(List<SimpleArithmeticEquation> equations, int startIndex, int endIndex, boolean includeAnswers, StringBuilder resultBuilder) {
		int size = endIndex - startIndex;
		int equationsLeftUnprinted = size % 3;

		for (int i = startIndex; i < startIndex + size - equationsLeftUnprinted; i = i + 3) {
			resultBuilder.append(String.format("%9s%18s%18s",
					SimpleArithmeticEquationUtil.formatEquation(equations.get(i), includeAnswers),
					SimpleArithmeticEquationUtil.formatEquation(equations.get(i + 1), includeAnswers),
					SimpleArithmeticEquationUtil.formatEquation(equations.get(i + 2), includeAnswers)));
			resultBuilder.append('\n');
		}

		switch (equationsLeftUnprinted) {
		case 1:
			resultBuilder.append(String.format("%9s",
					SimpleArithmeticEquationUtil.formatEquation(equations.get(startIndex + size - equationsLeftUnprinted), includeAnswers)));
			resultBuilder.append('\n');
			break;
		case 2:
			resultBuilder.append(String.format("%9s%18s",
					SimpleArithmeticEquationUtil.formatEquation(equations.get(startIndex + size - equationsLeftUnprinted), includeAnswers),
					SimpleArithmeticEquationUtil.formatEquation(equations.get(startIndex + size - equationsLeftUnprinted + 1), includeAnswers)));
			resultBuilder.append('\n');
			break;
		}
	}

	private static void writeDocxEquationsFile(String equations) {
		DocxFileGeneratorConfiguration equationsFileConfiguration = new DocxFileGeneratorConfiguration();
		equationsFileConfiguration.setLineSize(DEFAULT_EQUATIONS_DOCX_FILE_LINE_SIZE);
		equationsFileConfiguration.setFontSize(DEFAULT_EQUATIONS_DOCX_FILE_FONT_SIZE);
		DocxFileGenerator.generateDocxFile(EQUATION_SHEET_DOCX_FILENAME, equations, equationsFileConfiguration);
	}

	private static void printMemorizationSet(List<String> wordSet) {
	    for (int i = 0; i < 6; i++) {
	        int startingLineWordIdex = i * 5;
	        System.out.format("%-16s%-16s%-16s%-16s%-16s", wordSet.get(startingLineWordIdex), wordSet.get(startingLineWordIdex + 1), wordSet.get(startingLineWordIdex + 2), wordSet.get(startingLineWordIdex + 3), wordSet.get(startingLineWordIdex + 4));
	        System.out.print("\n");
	    }
	}

}
