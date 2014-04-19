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
		SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100, 1);
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 100);
		
		
		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(2, 1);
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 2);
		
		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(1, 1);
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 1);
		
		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100000, 100000);
		org.junit.Assert.assertTrue(sheet.getEquations().size() == 100000);
		
		// this exceeds array size VM limit 
//		sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(Integer.MAX_VALUE, 1);
//		org.junit.Assert.assertTrue(sheet.getEquations().size() == Integer.MAX_VALUE);
	}
	
	@Test
	public void testZeroEquationsQuantity() {
		try {
			new SimpleArithmeticEquationSheetGenerator().generateSheet(0, 1);
			org.junit.Assert.fail("SimpleArithmeticEquationSheetGenerator should have thrown an exception b/c of 0 as the equationsOnSheet parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testNegativeEquationsQuantity() {
		try {
			new SimpleArithmeticEquationSheetGenerator().generateSheet(-1, 1);
			org.junit.Assert.fail("SimpleArithmeticEquationSheetGenerator should have thrown an exception b/c of of the negative equationsOnSheet parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testNegativeDuplicatesMaxQuantity() {
		try {
			new SimpleArithmeticEquationSheetGenerator().generateSheet(100, -1);
			org.junit.Assert.fail("SimpleArithmeticEquationSheetGenerator should have thrown an exception b/c of the negative duplicatesMaxQuantity parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testEquationsOfSameTypeQuantity() {
		for (int i = 0; i < TEST_SAMPLES_QUANTITY; i++) {
			SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100, 1);
			
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
			SimpleArithmeticEquationSheet sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100, 1);
			
			int duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 1);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100, 2);
			duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 2);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100, 5);
			duplicatesQuantity = calculateDuplicatesQuantity(sheet);
			org.junit.Assert.assertTrue("duplicatesQuantity = " + duplicatesQuantity, duplicatesQuantity <= 5);
			
			sheet = new SimpleArithmeticEquationSheetGenerator().generateSheet(100, 0);
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
}
