package com.kaleb.allies;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void quickRunMethod() throws IOException {
        App appClass = new App();
        appClass.readBookAndStopWords();
    }

    //Test fails if read in List is empty
    @Test
    public void inputValidFileShouldReturnNullWhenGivenEmptyFile() throws IOException {
        App appClass = new App();
        assertEquals(null, appClass.readInTextFile("blank-file"));
    }

    @Test
    public void fileContainingMultipleIdenticalWordsShouldPrintTotalAmountOfSingleWord() throws IOException {
        HashMap<String, Integer> testAllLinesMap = new HashMap<>();
        App appClass = new App();

        //assertEquals("belted        count: 1", appClass.wordCountingLogic(testAllLinesMap, "belted"));
    }


}
