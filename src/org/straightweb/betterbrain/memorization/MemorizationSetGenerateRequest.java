package org.straightweb.betterbrain.memorization;

public class MemorizationSetGenerateRequest {
    
    private static final int DEFAULT_QUANTITY = 30;
    private static final String DEFAULT_PART_OF_SPEECH = "noun";
    private static final double DEFAULT_IPM = 20d;

    private int quantity;
    private String partOfSpeech;
    private double minimalIpm;
    
    public MemorizationSetGenerateRequest(String partOfSpeech, double minimalIpm, int quantity) {
        this.quantity = quantity;
        this.partOfSpeech = partOfSpeech;
        this.minimalIpm = minimalIpm;
    }
    
    public MemorizationSetGenerateRequest(String partOfSpeech, double minimalIpm) {
        this.partOfSpeech = partOfSpeech;
        this.minimalIpm = minimalIpm;
    }
    
    public MemorizationSetGenerateRequest() {
        this(DEFAULT_PART_OF_SPEECH, DEFAULT_IPM, DEFAULT_QUANTITY);
    }
    
    public int getQuantity() {
        return quantity;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }
    
    public double getMinimalIpm() {
        return minimalIpm;
    }
}
