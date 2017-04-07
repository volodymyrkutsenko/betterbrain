package org.straightweb.betterbrain.stroop;

import java.util.*;

public class EntriesGenerator {

	public static List<WordEntry> generateWordEntries(EntriesGeneratorRequest request) {
		int colorsPoolSize = (request.getQuantity() / Color.values().length) + 1;
		List<Color> colorPool = generateColorPool(colorsPoolSize);
		List<Color> textPool = generateColorPool(colorsPoolSize);
		List<WordEntry> wordEntries = new ArrayList<>();
		for (int i = 0; i < request.getQuantity(); i++) {
			WordEntry entry = new WordEntry(textPool.get(i), colorPool.get(i));
			wordEntries.add(entry);
		}
		return wordEntries;
	}

	private static List<Color> generateColorPool(int colorPoolSize) {
		int colorQuantity = Color.values().length;
		List<Color> colorPool = new ArrayList<>(colorQuantity * colorPoolSize);
		for (Color color : Color.values()) {
			for (int i = 0; i < colorPoolSize; i++) {
				colorPool.add(color);
			}
		}
		Collections.shuffle(colorPool);
		return colorPool;
	}

}
