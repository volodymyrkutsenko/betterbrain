package org.straightweb.betterbrain.arithmetics;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleArithmeticEquationSheetGeneratorTest {
	
	private static final int TEST_SAMPLES_QUANTITY = 100;
	
	@Test
	public void testEquationsQuantity() {
		SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1));
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 100);
		
		
		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(2, 1));
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 2);
		
		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(1, 1));
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 1);
		
		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100000, 100000));
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 100000);
		
		// this exceeds array size VM limit 
//		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(Integer.MAX_VALUE, 1);
//		org.junit.Assert.assertTrue(sheet.getEquations().size() == Integer.MAX_VALUE);
	}
	
	@Test
	public void testEquationsOfSameTypeQuantity() {
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1));
			
			int additionExamplesQuantity = calculateExamplesQuantity(sheet, ArithmeticOperation.ADDITION);
			org.junit.Assert.assertTrue("ArithmeticOperation.ADDITION quantity is " + additionExamplesQuantity, additionExamplesQuantity  < 36);
			
			int subtractionExamplesQuantity = calculateExamplesQuantity(sheet, ArithmeticOperation.SUBTRACTION);
			org.junit.Assert.assertTrue("ArithmeticOperation.SUBTRACTION quantity is " + subtractionExamplesQuantity, subtractionExamplesQuantity < 36);
			
			int multiplicationExamplesQuantity = calculateExamplesQuantity(sheet, ArithmeticOperation.MULTIPLICATION);
			org.junit.Assert.assertTrue("ArithmeticOperation.MULTIPLICATION quantity is " + multiplicationExamplesQuantity, multiplicationExamplesQuantity < 36);
		}
	}
	
	private static int calculateExamplesQuantity(SimpleArithmeticEquationSheet sheet, ArithmeticOperation operation) {
		int quantity = 0;
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			if (equation.getOperation() == operation) {
				quantity++;
			}
		}
		
		return quantity;
	}
	
	@Test
	public void testEquationsDuplicates() {
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1));
			
			int duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 1);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 2));
			duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 2);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 5));
			duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 5);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 0));
			duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 0);
		}
	}
	
	private static int calculateDuplicatesQuantity(SimpleArithmeticEquationSheet sheet) {
		List<SimpleArithmeticEquation> checkedEquations = new ArrayList<>(sheet.getEquations().size());
		int duplicatesQuantity = 0;
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			if (checkedEquations.contains(equation)) {
				continue;
			}
			int equationEntryCount = 0;
			for (SimpleArithmeticEquation equationToCheck : sheet.getEquations()) {
				if (equation.equals(equationToCheck)) {
					equationEntryCount++;
					if (equationEntryCount > 1) {
						duplicatesQuantity++;
					}
				}
			}
			checkedEquations.add(equation);
		}
		return duplicatesQuantity;
	}
	
	@Test
	public void testZeroArgumentedEquationsLimit() {
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 3));
			
			int zeroArgumentedEquationsQuantity = calculateZeroArgumentedEquations(sheet);
			org.junit.Assert.assertTrue("zeroArgumentedEquationsQuantity = " + zeroArgumentedEquationsQuantity, zeroArgumentedEquationsQuantity <= 3);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 2));
			zeroArgumentedEquationsQuantity = calculateZeroArgumentedEquations(sheet);
			org.junit.Assert.assertTrue("zeroArgumentedEquationsQuantity = " + zeroArgumentedEquationsQuantity, zeroArgumentedEquationsQuantity <= 2);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 5));
			zeroArgumentedEquationsQuantity = calculateZeroArgumentedEquations(sheet);
			org.junit.Assert.assertTrue("zeroArgumentedEquationsQuantity = " + zeroArgumentedEquationsQuantity, zeroArgumentedEquationsQuantity <= 5);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 0));
			zeroArgumentedEquationsQuantity = calculateZeroArgumentedEquations(sheet);
			org.junit.Assert.assertTrue("zeroArgumentedEquationsQuantity = " + zeroArgumentedEquationsQuantity, zeroArgumentedEquationsQuantity <= 0);
		}
	}
	
	private static int calculateZeroArgumentedEquations(SimpleArithmeticEquationSheet sheet) {
		int zeroArgumentedEquationsQuantity = 0;
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			if (equation.isZeroArgumented()) {
				zeroArgumentedEquationsQuantity++;
			}
		}
		return zeroArgumentedEquationsQuantity;
	}
	
	
	@Test
	public void testZeroResultEquationsLimit() {
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 4, 3));
			
			int zeroResultEquationsQuantity = calculateZeroResultEquations(sheet);
			org.junit.Assert.assertTrue("zeroResultEquationsQuantity = " + zeroResultEquationsQuantity, zeroResultEquationsQuantity <= 3);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 4, 2));
			zeroResultEquationsQuantity = calculateZeroResultEquations(sheet);
			org.junit.Assert.assertTrue("zeroResultEquationsQuantity = " + zeroResultEquationsQuantity, zeroResultEquationsQuantity <= 2);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 4, 5));
			zeroResultEquationsQuantity = calculateZeroResultEquations(sheet);
			org.junit.Assert.assertTrue("zeroResultEquationsQuantity = " + zeroResultEquationsQuantity, zeroResultEquationsQuantity <= 5);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(new EquationSheetGenerateRequest(100, 1, 4, 0));
			zeroResultEquationsQuantity = calculateZeroResultEquations(sheet);
			org.junit.Assert.assertTrue("zeroResultEquationsQuantity = " + zeroResultEquationsQuantity, zeroResultEquationsQuantity <= 0);
		}
	}
	
	private static int calculateZeroResultEquations(SimpleArithmeticEquationSheet sheet) {
		int zeroResultEquationsQuantity = 0;
		for (SimpleArithmeticEquation equation : sheet.getEquations()) {
			if (equation.isZeroResult()) {
				zeroResultEquationsQuantity++;
			}
		}
		return zeroResultEquationsQuantity;
	}
}
