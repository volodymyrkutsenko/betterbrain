package org.straightweb.betterbrain.stroop;

public class EntriesGeneratorRequest {

	private int quantity;

	public EntriesGeneratorRequest(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
