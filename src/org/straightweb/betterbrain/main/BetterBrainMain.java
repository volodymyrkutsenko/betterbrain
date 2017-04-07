package org.straightweb.betterbrain.main;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	private static final BigInteger DEFAULT_EQUATIONS_DOCX_FILE_LINE_SIZE = BigInteger.valueOf(320);
	private static final int DEFAULT_EQUATIONS_DOCX_FILE_FONT_SIZE = 22;
	private static final String EQUATIONS_ARGUMENT = "--equations";
	private static final String WORDSET_ARGUMENT = "--wordset";
	private static final String EXPORT_FILENAME_ARGUMENT = "--filename";
	private static final String DOCX_ARGUMENT = "--docx";
	private static final String HELP_ARGUMENT = "--help";
	private static final int LINE_NUMBER = 6;
	private static final int WORDS_IN_LINE_NUMBER = 5;

	public static void main(String[] args) {
		if (args.length > 0 && HELP_ARGUMENT.equalsIgnoreCase(args[0])) {
			System.out.println("For usage follow one of such call patterns: ");
			System.out.println("'betterbrain.jar " + EQUATIONS_ARGUMENT + " [" + DOCX_ARGUMENT + "] " + EXPORT_FILENAME_ARGUMENT + " <FILEMANE>'");
			System.out.println("'betterbrain.jar " + WORDSET_ARGUMENT + " " + EXPORT_FILENAME_ARGUMENT + " <FILEMANE>'");
			return;
		}

		String exportFilename = retrieveExportFilename(args);

		if (StringUtils.isEmpty(exportFilename)) {
			System.out.println("No filename provided for export! Please provide the export filename by using the " + EXPORT_FILENAME_ARGUMENT + " launch argument");
			return;
		}

		boolean needPrintEquations = isKeyPresent(args, EQUATIONS_ARGUMENT);
		boolean needPrintWordset = isKeyPresent(args, WORDSET_ARGUMENT);

		if (needPrintEquations && needPrintWordset) {
			System.out.println(EQUATIONS_ARGUMENT + " and " + WORDSET_ARGUMENT + " cannot be used together! Please choose either equations or memorization wordset export launch argument.");
			return;
		}

		if (needPrintEquations) {
			StringBuilder equationsBuilder = new StringBuilder();
			for (int i = 0; i < DEFAULT_EQUATION_SHEETS_NUMBER; i++) {
				EquationSheetGenerateRequest sheetGenerateRequest = new EquationSheetGenerateRequest();

				SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(sheetGenerateRequest);

				appendEquations(sheet.getEquations(), 0, sheet.getEquations().size() / 2, false, equationsBuilder);
				equationsBuilder.append('\n');
				equationsBuilder.append('\n');
				appendEquations(sheet.getEquations(), sheet.getEquations().size() / 2, sheet.getEquations().size(), false, equationsBuilder);
				equationsBuilder.append('\n');
				equationsBuilder.append('\n');
			}

			if (isKeyPresent(args, DOCX_ARGUMENT)) {
				writeDocxEquationsFile(equationsBuilder.toString(), exportFilename);
			} else {
				writeSimpleTextFile(equationsBuilder.toString(), exportFilename);
			}
		}

		if (needPrintWordset) {
			StringBuilder wordsetBuilder = new StringBuilder();
			printMemorizationSet(new MemorizationSetGenerator().generateSet(new MemorizationSetGenerateRequest()), wordsetBuilder);
			if (isKeyPresent(args, DOCX_ARGUMENT)) {
				System.out.println(DOCX_ARGUMENT + " argument cannot be used with the " + WORDSET_ARGUMENT + " argument.");
			} else {
				writeSimpleTextFile(wordsetBuilder.toString(), exportFilename);
			}

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

	private static String retrieveExportFilename(String[] args) {
		boolean retrieveFilename = false;
		for (String arg : args) {
			if (retrieveFilename) {
				return arg;
			}
			if (EXPORT_FILENAME_ARGUMENT.equalsIgnoreCase(arg)) {
				retrieveFilename = true;
			}
		}
		return StringUtils.EMPTY;
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

	private static void writeDocxEquationsFile(String equations, String fileName) {
		DocxFileGeneratorConfiguration equationsFileConfiguration = new DocxFileGeneratorConfiguration();
		equationsFileConfiguration.setLineSize(DEFAULT_EQUATIONS_DOCX_FILE_LINE_SIZE);
		equationsFileConfiguration.setFontSize(DEFAULT_EQUATIONS_DOCX_FILE_FONT_SIZE);
		DocxFileGenerator.generateDocxFile(fileName, equations, equationsFileConfiguration);
	}

	private static void writeSimpleTextFile(String wordset, String fileName) {
		try (FileWriter fileWriter = new FileWriter(fileName)) {
			fileWriter.write(wordset);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private static void printMemorizationSet(List<String> wordSet, StringBuilder resultBuilder) {
		for (int i = 0; i < LINE_NUMBER; i++) {
			int startingLineWordIndex = i * WORDS_IN_LINE_NUMBER;
			resultBuilder.append(String.format("%-16s%-16s%-16s%-16s%-16s", wordSet.get(startingLineWordIndex), wordSet.get(startingLineWordIndex + 1), wordSet.get(startingLineWordIndex + 2), wordSet.get(startingLineWordIndex + 3), wordSet.get(startingLineWordIndex + 4)));
			resultBuilder.append("\n");
		}
	}

}
