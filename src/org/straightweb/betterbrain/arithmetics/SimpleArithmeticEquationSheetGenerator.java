package org.straightweb.betterbrain.arithmetics;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SimpleArithmeticEquationSheetGenerator {

	private static final double EQUATIONS_OF_SAME_TYPE_RATIO = 0.35f;
	
	private final Map<ArithmeticOperation, SimpleArithmeticEquationGenerator> EQUATION_GENERATORS = new EnumMap<>(ArithmeticOperation.class);
	private final Random RANDOM = new Random();
	
	{
		EQUATION_GENERATORS.put(ArithmeticOperation.ADDITION, new SimpleAdditionEquationGenerator());
		EQUATION_GENERATORS.put(ArithmeticOperation.SUBTRACTION, new SimpleSubtractionEquationGenerator());
		EQUATION_GENERATORS.put(ArithmeticOperation.MULTIPLICATION, new SimpleMultiplicationEquationGenerator());
	}
	
	public SimpleArithmeticEquationSheet generateSheet(EquationSheetGenerateRequest sheetGenerateRequest) {
		int equationsOnSheet = sheetGenerateRequest.getEquationsOnSheet();
		
		List<SimpleArithmeticEquation> equations = new ArrayList<>(equationsOnSheet);
		int equationsOfSameTypeMaxQuantity = calculateEquationsOfSameTypeMaxQuantity(equationsOnSheet);
		
		int equationsGeneratedForSheet = 0;
		Map<ArithmeticOperation, Integer> examplesTypeQuantities = new EnumMap<>(ArithmeticOperation.class);
		examplesTypeQuantities.put(ArithmeticOperation.ADDITION, 0);
		examplesTypeQuantities.put(ArithmeticOperation.SUBTRACTION, 0);
		examplesTypeQuantities.put(ArithmeticOperation.MULTIPLICATION, 0);
		int duplicatesQuantity = 0;
		int zeroResultExamplesQuantity = 0;
		int zeroArgumentedExamplesQuantity = 0;
		while (equationsGeneratedForSheet < equationsOnSheet) {
			ArithmeticOperation exampleOperation = getRandomArithmeticOperation();
			SimpleArithmeticEquation equation = EQUATION_GENERATORS.get(exampleOperation).generateEquation();
			
			boolean isZeroResult = false;
			if (equation.isZeroResult()) {
				if (zeroResultExamplesQuantity < sheetGenerateRequest.getZeroResultEquationsMaxQuantity()) {
					isZeroResult = true;
				} else {
					continue;
				}
			}
			
			boolean isZeroArgumented = false;
			if (equation.isZeroArgumented()) {
				if (zeroArgumentedExamplesQuantity < sheetGenerateRequest.getZeroArgumentedEquationsMaxQuantity()) {
					isZeroArgumented = true;
				} else {
					continue;
				}
			}
			
			boolean isDuplicate = false;
			if (equations.contains(equation)) {
				if (duplicatesQuantity < sheetGenerateRequest.getDuplicatesMaxQuantity()) {
					isDuplicate = true;
				} else {
					continue;
				}
			}
			
			Integer equationTypeQuantity = examplesTypeQuantities.get(exampleOperation); 
			if (equationTypeQuantity == equationsOfSameTypeMaxQuantity) {
				continue;
			}
			
			equations.add(equation);
			
			examplesTypeQuantities.put(exampleOperation, equationTypeQuantity + 1);
			
			if (isZeroResult) {
				zeroResultExamplesQuantity++;
			}
			if (isZeroArgumented) {
				zeroArgumentedExamplesQuantity++;
			}
			if (isDuplicate) {
				duplicatesQuantity++;
			}
			
			equationsGeneratedForSheet++;
		}
		return new SimpleArithmeticEquationSheet(equations);
	}
	
	private ArithmeticOperation getRandomArithmeticOperation() {
		return ArithmeticOperation.values()[RANDOM.nextInt(ArithmeticOperation.values().length)];
	}
	
	private static int calculateEquationsOfSameTypeMaxQuantity(int equationsOnSheet) {
		int result = (int) (equationsOnSheet * EQUATIONS_OF_SAME_TYPE_RATIO);
		while (result / EQUATIONS_OF_SAME_TYPE_RATIO < equationsOnSheet) {
			result++;
		}
		return result;
	}
	
}
