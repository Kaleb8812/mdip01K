package com.kaleb.allies;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {

    protected HashMap<String, Integer> allLinesMap = new HashMap<>();

    protected ArrayList readBookAndStopWords(int i) throws IOException {
        List bookWordList = readInTextFile("frequency-test");
        List stopWordListWithBlanks = readInTextFile("stop-words");
        ArrayList<String> stopWordListWithoutBlanks = stopWordListFormatting(stopWordListWithBlanks);

        lineSplitRules(bookWordList, stopWordListWithoutBlanks);

        return wordFrequencyExportRules();
    }

    protected List readInTextFile(String fileName) throws IOException {
        List allLinesFromFile = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/" + fileName + ".txt"));

        if (!allLinesFromFile.isEmpty()) {
            return allLinesFromFile;
        } else {
            return null;
        }
    }

    protected ArrayList<String> stopWordListFormatting(List stopWordListWithBlanks) {
        ArrayList<String> stopWordListWithoutBlanks = new ArrayList<>();
        String singleLine;

        for (Object stopWordListWithBlank : stopWordListWithBlanks) {
            singleLine = stopWordListWithBlank.toString();
            if (!singleLine.isEmpty() && !singleLine.startsWith("#")) {
                stopWordListWithoutBlanks.add(stopWordListWithBlank.toString());
            }
        }

        return stopWordListWithoutBlanks;
    }

    protected void lineSplitRules(List bookWordList, ArrayList stopWordListWithoutBlanks) {
        //String singleLine;

        for (Object allLines : bookWordList) {
            String[] wordsSplitBySpace = stringSpaceRules(allLines.toString());
            for (String wordFromLineSplitBySpace : wordsSplitBySpace) {
                    wordFromLineSplitBySpace = characterDeleteRules(wordFromLineSplitBySpace);
                if (!stopWordListWithoutBlanks.contains(wordFromLineSplitBySpace)) {
                    wordCountingRules(wordFromLineSplitBySpace);
                }
            }
        }
    }

    protected String[] stringSpaceRules(String singleLine) {
        if (singleLine.contains("-")) {
            singleLine = singleLine.replaceAll("-", " ");
        }

        return singleLine.split("\\s+");
    }

    protected String characterDeleteRules(String wordFromLineSplitBySpace) {
        wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("[0-9,;:.?]", "");

        return wordFromLineSplitBySpace;
    }

    protected ArrayList wordFrequencyExportRules() {
        ArrayList topOneHundred = new ArrayList(); //Sort values numerically after populating hashmap

        for (String name : allLinesMap.keySet()) {
            int value = allLinesMap.get(name);
            if (value >= 4) {
                topOneHundred.add(name);
            }

//          Eventually replaced with writing to file or other export style
            System.out.println(name + "      count: " + value);
        }

        return topOneHundred;
    }

    protected void wordCountingRules(String wordFromLineSplitBySpace) {
        // Sort values numerically as they pass/fall behind each other
        if (!allLinesMap.containsKey(wordFromLineSplitBySpace)) {
            allLinesMap.put(wordFromLineSplitBySpace, 1);
        } else {
            allLinesMap.put(wordFromLineSplitBySpace, allLinesMap.get(wordFromLineSplitBySpace) + 1);
        }
    }
}
