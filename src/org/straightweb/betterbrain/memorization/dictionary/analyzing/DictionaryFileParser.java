package org.straightweb.betterbrain.memorization.dictionary.analyzing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryFileParser {

    private final static Logger LOGGER = Logger.getLogger(DictionaryFileParser.class.getName());
    
    private static final Pattern DICTIONARY_RECORD_PATTERN = Pattern.compile("^(\\d+)\\s([\\d\\.]+)\\s([^\\s]+)\\s([^\\s]+)$");

    public List<DictionaryRecord> retrieveAllRecords(InputStream inputFile) {
        List<DictionaryRecord> dictionaryRecords = new LinkedList<>();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(inputFile);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    dictionaryRecords.add(parseLine(line));
                } catch (DictionaryAnalyzingException e) {
                    LOGGER.log(Level.SEVERE, "An exception occurred while processing a line of the file", e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while processing the file", e);
        } finally { 
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "An exception occurred while processing the file", e);
                }
            }
        }
        
        return dictionaryRecords;
    }

    private DictionaryRecord parseLine(String line) throws DictionaryAnalyzingException {
        Matcher dictionaryRecordMatcher = DICTIONARY_RECORD_PATTERN.matcher(line);
        if (dictionaryRecordMatcher.find()) {
            int frequencyRate = Integer.parseInt(dictionaryRecordMatcher.group(1));
            double ipm = Double.parseDouble(dictionaryRecordMatcher.group(2));
            String word = dictionaryRecordMatcher.group(3);
            String partOfSpeech = dictionaryRecordMatcher.group(4);
            return new DictionaryRecord(frequencyRate, ipm, word, partOfSpeech);
        }

        throw new DictionaryAnalyzingException("Malformed dictionary line: " + line);
    }
}
