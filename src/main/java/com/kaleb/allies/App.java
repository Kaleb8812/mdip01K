package com.kaleb.allies;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class App {
    public static void main(String[] args) throws IOException {
    }

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

    private void wordCounter(List allLines) {
        HashMap<String, Integer> allLinesMap = new HashMap<>();
        String singleLine = "";
        for (Object allLine : allLines) {
            singleLine = allLine.toString();
            String[] wordsSplitBySpace = singleLine.split("\\s+");

            for (String aWordsSplitBySpace : wordsSplitBySpace) {
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
