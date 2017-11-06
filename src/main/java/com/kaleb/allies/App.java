package com.kaleb.allies;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class App {
    //public static void main(String[] args) throws IOException {
    //}

    Boolean readBookAndStopWords() throws IOException {
        Boolean listEmpty = false;

        List allLines = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/sample.txt"));


        if (allLines.isEmpty()) {
            listEmpty = true;
        } else {
            wordCounter(allLines);
        }

        return listEmpty;
    }

    private void wordCounter(List allLines) throws IOException {
        List stopWordList = FileUtils.readLines(new File("C:/Workspaces/IDEA/mdip01K/src/main/resources/stop-words.txt"));
        HashMap<String, Integer> allLinesMap = new HashMap<>();
        String singleLine = "";
        for (Object allLine : allLines) {
            singleLine = allLine.toString();
            String[] wordsSplitBySpace = singleLine.split("\\s+");
            for (String aWordsSplitBySpace : wordsSplitBySpace) {
                aWordsSplitBySpace = aWordsSplitBySpace.replaceAll("\\.", "");
                if (!stopWordList.contains(/*StringUtils.toLowerCase*/(aWordsSplitBySpace))) {

                    //Filter against stop word list; Start word frequency counter
                    if (!allLinesMap.containsKey(aWordsSplitBySpace)) {
                        allLinesMap.put(aWordsSplitBySpace, 1);
                    } else {
                        allLinesMap.put(aWordsSplitBySpace, allLinesMap.get(aWordsSplitBySpace) + 1);
                    }
                    System.out.println(aWordsSplitBySpace + "       count: " + allLinesMap.get(aWordsSplitBySpace));
                }
            }
        }
    }
}
