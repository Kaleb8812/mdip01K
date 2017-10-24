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
        Boolean listEmpty = true;

        List singleLine = FileUtils.readLines(new File("c:/Workspaces/IDEA/mdip01K/sample.txt"));
        if (singleLine.isEmpty()) {
            listEmpty = false;
        }

        return listEmpty;
    }
}
