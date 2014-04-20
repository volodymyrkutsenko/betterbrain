package org.straightweb.betterbrain.arithmetics;

public class EquationSheetGenerateRequest {
	
	private static final int ZERO_ARGUMENTED_DEFAULT_QTY = 4;
	private static final int ZERO_RESULT_DEFAULT_QTY = 4;
	private static final int DUPLICATES_DEFAULT_QTY = 0;
	private static final int EQUATIONS_ON_SHEET_DEFAULT_QTY = 100;

	private int equationsOnSheet;
	private int duplicatesMaxQuantity;
	private int zeroArgumentedEquationsMaxQuantity;
	
	public EquationSheetGenerateRequest(int equationsOnSheet,
			int duplicatesMaxQuantity, int zeroArgumentedEquationsMaxQuantity,
			int zeroResultEquationsMaxQuantity) {
		if (equationsOnSheet < 1) {
			throw new IllegalArgumentException("The equationsOnSheet parameter must be greater than zero");
		}
		if (duplicatesMaxQuantity < 0) {
			throw new IllegalArgumentException("The duplicatesMaxQuantity parameter cannot be negative");
		}
		if (zeroArgumentedEquationsMaxQuantity < 0) {
			throw new IllegalArgumentException("The zeroArgumentedEquationsMaxQuantity parameter cannot be negative");
		}
		if (zeroResultEquationsMaxQuantity < 0) {
			throw new IllegalArgumentException("The zeroResultEquationsMaxQuantity parameter cannot be negative");
		}
		this.equationsOnSheet = equationsOnSheet;
		this.duplicatesMaxQuantity = duplicatesMaxQuantity;
		this.zeroArgumentedEquationsMaxQuantity = zeroArgumentedEquationsMaxQuantity;
		this.zeroResultEquationsMaxQuantity = zeroResultEquationsMaxQuantity;
	}
	
	public EquationSheetGenerateRequest(int equationsOnSheet,
			int duplicatesMaxQuantity, int zeroArgumentedEquationsMaxQuantity) {
		this(equationsOnSheet, duplicatesMaxQuantity, zeroArgumentedEquationsMaxQuantity, ZERO_RESULT_DEFAULT_QTY);
	}
	
	public EquationSheetGenerateRequest(int equationsOnSheet,
			int duplicatesMaxQuantity) {
		this(equationsOnSheet, duplicatesMaxQuantity, ZERO_ARGUMENTED_DEFAULT_QTY, ZERO_RESULT_DEFAULT_QTY);
	}
	
	public EquationSheetGenerateRequest(int equationsOnSheet) {
		this(equationsOnSheet, DUPLICATES_DEFAULT_QTY, ZERO_ARGUMENTED_DEFAULT_QTY, ZERO_RESULT_DEFAULT_QTY);
	}
	
	public EquationSheetGenerateRequest() {
		this(EQUATIONS_ON_SHEET_DEFAULT_QTY, DUPLICATES_DEFAULT_QTY, ZERO_ARGUMENTED_DEFAULT_QTY, ZERO_RESULT_DEFAULT_QTY);
	}

	private int zeroResultEquationsMaxQuantity;

	public int getEquationsOnSheet() {
		return equationsOnSheet;
	}

	public int getDuplicatesMaxQuantity() {
		return duplicatesMaxQuantity;
	}

	public int getZeroArgumentedEquationsMaxQuantity() {
		return zeroArgumentedEquationsMaxQuantity;
	}

	public int getZeroResultEquationsMaxQuantity() {
		return zeroResultEquationsMaxQuantity;
	}

}
