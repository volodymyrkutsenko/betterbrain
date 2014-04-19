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
	
	public SimpleArithmeticEquationSheet generateSheet(int equationsOnSheet, int duplicatesMaxQuantity) {
		if (equationsOnSheet < 1) {
			throw new IllegalArgumentException("The equationsOnSheet parameter must be greater than zero");
		}
		if (duplicatesMaxQuantity < 0) {
			throw new IllegalArgumentException("The duplicatesMaxQuantity parameter cannot be negative");
		}
		
		List<SimpleArithmeticEquation> equations = new ArrayList<>(equationsOnSheet);
		int equationsOfSameTypeMaxQuantity = calculateEquationsOfSameTypeMaxQuantity(equationsOnSheet);
		
		int equationsGeneratedForSheet = 0;
		Map<ArithmeticOperation, Integer> examplesTypeQuantities = new EnumMap<>(ArithmeticOperation.class);
		examplesTypeQuantities.put(ArithmeticOperation.ADDITION, 0);
		examplesTypeQuantities.put(ArithmeticOperation.SUBTRACTION, 0);
		examplesTypeQuantities.put(ArithmeticOperation.MULTIPLICATION, 0);
		int duplicatesQuantity = 0;
		while (equationsGeneratedForSheet < equationsOnSheet) {
			ArithmeticOperation exampleOperation = getRandomArithmeticOperation();
			SimpleArithmeticEquation equation = EQUATION_GENERATORS.get(exampleOperation).generateEquation();
			if (equations.contains(equation)) {
				if (duplicatesQuantity < duplicatesMaxQuantity) {
					duplicatesQuantity++;
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
