package com.kaleb.allies;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
    }

    Boolean ReadBookAndStopWords() throws IOException {
        Boolean listEmpty = false;

        List allLines = FileUtils.readLines(new File("c:/Workspaces/IDEA/mdip01K/sample.txt"));
        if (allLines.isEmpty()) {
            listEmpty = true;
        } else {
            String singleLine = "";
            for (Object allLine : allLines) {
                singleLine = allLine.toString();
                String[] wordsSplitBySpace = singleLine.split("\\s+");
                for (String aWordsSplitBySpace : wordsSplitBySpace) {
                    //Filter against stop word list; Start word frequency counter
                    System.out.println(aWordsSplitBySpace);
                }
            }

        }

        return listEmpty;
    }
}
