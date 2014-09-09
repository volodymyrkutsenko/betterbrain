package org.straightweb.betterbrain.memorization;

import java.util.LinkedList;
import java.util.List;
import org.straightweb.betterbrain.memorization.dictionary.analyzing.DictionaryRecord;
import org.straightweb.betterbrain.memorization.dictionary.analyzing.DictionaryRecordManager;

public class MemorizationSetGenerator {
    
    private DictionaryRecordManager dictionaryRecordManager = new DictionaryRecordManager();

    public List<String> generateSet(MemorizationSetGenerateRequest request) {
        List<DictionaryRecord> records = dictionaryRecordManager.generateRandomWordList(request.getPartOfSpeech(), request.getMinimalIpm(), request.getQuantity());
        List<String> wordSet = new LinkedList<>();
        records.stream().forEach(record -> wordSet.add(record.getWord()));
        return wordSet;
    }
}
