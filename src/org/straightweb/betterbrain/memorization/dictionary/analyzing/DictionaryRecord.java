package org.straightweb.betterbrain.memorization.dictionary.analyzing;

public class DictionaryRecord {

    private int frequencyRate;
    private double ipm;
    private String word;
    private String partOfSpeech;

    public DictionaryRecord(int frequencyRate, double ipm, String word, String partOfSpeech) {
        this.frequencyRate = frequencyRate;
        this.ipm = ipm;
        this.word = word;
        this.partOfSpeech = partOfSpeech;
    }

    public int getFrequencyRate() {
        return frequencyRate;
    }

    public double getIpm() {
        return ipm;
    }

    public String getWord() {
        return word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

}
