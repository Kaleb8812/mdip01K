package com.kaleb.allies;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class App {

    protected void readBookAndStopWords() throws IOException {

        List bookWordList = readBook();
        List stopWordList = readStopWords();
        HashMap<String, Integer> allLinesMap = new HashMap<>();
        String singleLine = "";
        for (Object allLine : bookWordList) {
            singleLine = allLine.toString();
            String[] wordsSplitBySpace = singleLine.split("\\s+");
            for (String wordFromLineSplitBySpace : wordsSplitBySpace) {
                wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("\\.", "");
                //Filter against stop word list; Start word frequency counter
                wordCountingLogic(allLinesMap, wordFromLineSplitBySpace);
            }
        }

    }

    protected List readBook() throws IOException {
        List allBookLines = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/sample.txt"));
        if (!allBookLines.isEmpty()) {
            return allBookLines;
        } else {
            return null;
        }
    }

    protected List readStopWords() throws IOException {
        List allStopWordLines = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/stop-words.txt"));
        if (!allStopWordLines.isEmpty()) {
            return allStopWordLines;
        } else {
            return null;
        }
    }


    protected String wordCountingLogic(HashMap<String, Integer> readWordsMap, String wordFromLineSplitBySpace) {

        if (!readWordsMap.containsKey(wordFromLineSplitBySpace)) {
            readWordsMap.put(wordFromLineSplitBySpace, 1);
        } else {
            readWordsMap.put(wordFromLineSplitBySpace, readWordsMap.get(wordFromLineSplitBySpace) + 1);
        }

        return wordFromLineSplitBySpace + "        count: " + readWordsMap.get(wordFromLineSplitBySpace);

    }


}
