package com.kaleb.allies;

import com.sun.tools.javac.util.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {

    protected ArrayList<String> stopWordListWithoutBlanks = new ArrayList<>();
    protected HashMap<String, Integer> allLinesMap = new HashMap<>();

    protected void readBookAndStopWords() throws IOException {

        String singleLine;
        List bookWordList = readInTextFile("sample");
        List stopWordListWithBlanks = readInTextFile("stop-words");


        for(int i=0; i<stopWordListWithBlanks.size(); i++) {
            singleLine = stopWordListWithBlanks.get(i).toString();
            if(!singleLine.isEmpty() && !singleLine.startsWith("#")){
                stopWordListWithoutBlanks.add(stopWordListWithBlanks.get(i).toString());
            }
        }

        for (Object allLines : bookWordList) {
            singleLine = allLines.toString();
            String[] wordsSplitBySpace = singleLine.split("\\s+");
            stringListFormattingRules(wordsSplitBySpace, stopWordListWithoutBlanks);
        }

        for (String name: allLinesMap.keySet()) {
            String value = allLinesMap.get(name).toString();
            System.out.println(name + "      count: " + value);
        }

    }

    private void stringListFormattingRules(String[] bookWordsSplitBySpace, ArrayList stopWords) {
        for (String wordFromLineSplitBySpace : bookWordsSplitBySpace) {
            wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("\\.", "");
            wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll(",", "");
            wordFromLineSplitBySpace = wordFromLineSplitBySpace.replaceAll("'", "");

            wordFromLineSplitBySpace = StringUtils.toLowerCase(wordFromLineSplitBySpace);

            /*if(!stopWords.contains(wordFromLineSplitBySpace)){
wo
            }
            //Replace apos, commas, uppercase, etc.
            //Filter against stop word list; Start word frequency counter
            //wordCountingLogic(allLinesMap, wordFromLineSplitBySpace);*/
            wordCountingLogic(wordFromLineSplitBySpace);
        }
    }

    protected List readInTextFile(String fileName) throws IOException {

        List allLinesFromFile = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/" + fileName + ".txt"));

        if (!allLinesFromFile.isEmpty()) {
            return allLinesFromFile;
        } else {
            System.out.println("Text input file " + fileName + " is empty");
            return null;
        }
    }

    protected void wordCountingLogic(String wordFromLineSplitBySpace) {

        if(!stopWordListWithoutBlanks.contains(wordFromLineSplitBySpace)) {
            if (!allLinesMap.containsKey(wordFromLineSplitBySpace)) {
                allLinesMap.put(wordFromLineSplitBySpace, 1);
            } else {
                allLinesMap.put(wordFromLineSplitBySpace, allLinesMap.get(wordFromLineSplitBySpace) + 1);
            }
        }

        //return wordFromLineSplitBySpace + "        count: " + allLinesMap.get(wordFromLineSplitBySpace);
    }
}
