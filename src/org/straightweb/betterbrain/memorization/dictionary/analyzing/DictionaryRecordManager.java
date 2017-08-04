package org.straightweb.betterbrain.memorization.dictionary.analyzing;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryRecordManager {

    private static final String DICTIONARY_FILE_NAME = "dictionary_rus.txt";
    
    private List<DictionaryRecord> records;

    public DictionaryRecordManager() {
        DictionaryFileParser parser = new DictionaryFileParser();
        records = parser.retrieveAllRecords(DictionaryRecordManager.class.getResourceAsStream(DICTIONARY_FILE_NAME));
    }
    
    public List<DictionaryRecord> generateRandomWordList(String partOfSpeech, double minimalIpm, int quantity) {
        List<DictionaryRecord> particularPartOfSpeechWordRecords = records.stream().filter(record -> partOfSpeech.equals(record.getPartOfSpeech()) && record.getIpm() >= minimalIpm).collect(Collectors.toList());
        Collections.shuffle(particularPartOfSpeechWordRecords);
        return particularPartOfSpeechWordRecords.subList(0, quantity);
    }
}
