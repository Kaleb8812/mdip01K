package com.kaleb.allies;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    protected HashMap<String, Integer> allLinesMap = new HashMap<>();

    protected ArrayList readBookAndStopWords(String readInTextFileName, int topWordAmount, String delimiterChoice) throws IOException {
        List bookWordList = readInTextFile(readInTextFileName);
        List stopWordListWithBlanks = readInTextFile("stop-words");
        ArrayList<String> stopWordListWithoutBlanks = stopWordListFormatting(stopWordListWithBlanks);
        lineSplitRules(bookWordList, stopWordListWithoutBlanks);

        return wordFrequencyExportRules(topWordAmount, delimiterChoice);
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
                stopWordListWithoutBlanks.add(stopWordListWithBlank.toString().toLowerCase());
            }
        }

        return stopWordListWithoutBlanks;
    }

    protected void lineSplitRules(List bookWordList, ArrayList stopWordListWithoutBlanks) {
        for (Object allLines : bookWordList) {
            String[] wordsSplitBySpace = stringSpaceRules(allLines.toString().toLowerCase());
            for (String wordFromLineSplitBySpace : wordsSplitBySpace) {
                wordFromLineSplitBySpace = characterDeleteRules(wordFromLineSplitBySpace);
                if (!stopWordListWithoutBlanks.contains(wordFromLineSplitBySpace) && !wordFromLineSplitBySpace.isEmpty()) {
                    wordCountingRules(wordFromLineSplitBySpace);
                }
            }
        }
    }

    protected String[] stringSpaceRules(String singleLine) {
        if (singleLine.contains("-") || singleLine.contains("—")) {
            singleLine = singleLine.replaceAll("[-—]", " ");
        }

        return singleLine.split("\\s+");
    }

    protected String characterDeleteRules(String wordFromLineSplitBySpace) {
        wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("[^A-Za-z]", "");

        return wordFromLineSplitBySpace;
    }

    protected ArrayList wordFrequencyExportRules(int topWordAmount, String delimiterChoice) {
        ArrayList topWordList = new ArrayList();
        Map<String, Integer> sortedMap = sortByValue(allLinesMap);

        int i = 0;
        for (String name : sortedMap.keySet()) {
            if (i < topWordAmount) {
                String value = sortedMap.get(name).toString();
                topWordList.add(name + delimiterChoice + value);
            }
            i++;
        }

        return topWordList;
    }

    protected void wordCountingRules(String wordFromLineSplitBySpace) {
        // Sort values numerically as they pass/fall behind each other
        if (!allLinesMap.containsKey(wordFromLineSplitBySpace)) {
            allLinesMap.put(wordFromLineSplitBySpace, 1);
        } else {
            allLinesMap.put(wordFromLineSplitBySpace, allLinesMap.get(wordFromLineSplitBySpace) + 1);
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
