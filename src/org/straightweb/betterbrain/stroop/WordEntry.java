package org.straightweb.betterbrain.stroop;

public class WordEntry {

	private Color text;
	private Color color;

	public WordEntry(Color text, Color color) {
		this.text = text;
		this.color = color;
	}

	public Color getText() {
		return text;
	}

	public void setText(Color text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
