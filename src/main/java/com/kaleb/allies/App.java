package com.kaleb.allies;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {

    protected HashMap<String, Integer> allLinesMap = new HashMap<>();

    protected void readBookAndStopWords() throws IOException {
        List bookWordList = readInTextFile("sample");
        List stopWordListWithBlanks = readInTextFile("stop-words");
        ArrayList<String> stopWordListWithoutBlanks = stopWordListFormatting(stopWordListWithBlanks);
        lineSplittingRules(bookWordList, stopWordListWithoutBlanks);
        wordFrequencyExportRules();
    }

    protected void wordFrequencyExportRules() {
        for (String name: allLinesMap.keySet()) {
            int value = allLinesMap.get(name);
//          Eventually replaced with writing to file
            System.out.println(name + "      count: " + value);
        }
    }

    protected ArrayList<String> stopWordListFormatting(List stopWordListWithBlanks) {
        ArrayList<String> stopWordListWithoutBlanks = new ArrayList<>();
        String singleLine;
        for(int i = 0; i<stopWordListWithBlanks.size(); i++) {
            singleLine = stopWordListWithBlanks.get(i).toString();
            if(!singleLine.isEmpty() && !singleLine.startsWith("#")){
                stopWordListWithoutBlanks.add(stopWordListWithBlanks.get(i).toString());
            }
        }
        return stopWordListWithoutBlanks;
    }

    protected void lineSplittingRules(List bookWordList, ArrayList stopWordListWithoutBlanks) {
        String singleLine;

        for (Object allLines : bookWordList) {
            singleLine = allLines.toString();
            String[] wordsSplitBySpace = singleLine.split("\\s+");
            for (String wordFromLineSplitBySpace : wordsSplitBySpace) {
                wordFromLineSplitBySpace = stringFormattingRules(wordFromLineSplitBySpace);
                if(!stopWordListWithoutBlanks.contains(wordFromLineSplitBySpace)) {
                    wordCountingRules(wordFromLineSplitBySpace);
                }
            }
        }
    }

    protected String stringFormattingRules(String wordFromLineSplitBySpace) {
//        Replace apostrophes, commas, other symbols, etc
//        Change language, case of letters, etc
//        wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll(",", "");
//        wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("'", "");
        wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("\\.", "");

        return wordFromLineSplitBySpace;
    }

    protected List readInTextFile(String fileName) throws IOException {

        List allLinesFromFile = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/" + fileName + ".txt"));

        if (!allLinesFromFile.isEmpty()) {
            return allLinesFromFile;
        } else {
            return null;
        }
    }

    protected void wordCountingRules(String wordFromLineSplitBySpace) {
        if (!allLinesMap.containsKey(wordFromLineSplitBySpace)) {
            allLinesMap.put(wordFromLineSplitBySpace, 1);
        } else {
            allLinesMap.put(wordFromLineSplitBySpace, allLinesMap.get(wordFromLineSplitBySpace) + 1);
        }
    }
}
