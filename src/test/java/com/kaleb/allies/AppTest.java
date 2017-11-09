package com.kaleb.allies;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void quickRunMethod() throws IOException {
        App appClass = new App();
        appClass.readBookAndStopWords();
    }

    @Test
    public void emptyFileShouldReturnNull() throws IOException {
        App appClass = new App();
        assertEquals(null, appClass.readInTextFile("blank-file"));
    }

    @Test
    public void populatedFileShouldReturnContentsAsList() throws IOException {
        List myList = new ArrayList();
        myList.add("Test line 1");
        myList.add("Test line 2");
        myList.add("Test line 3");
        App appClass = new App();
        List testPopList = appClass.readInTextFile("populated-test");
        assertEquals(myList, testPopList);
    }

    @Test
    public void fileContainingMultipleIdenticalWordsShouldPrintTotalAmountOfSingleWord() throws IOException {
        HashMap<String, Integer> testAllLinesMap = new HashMap<>();
        App appClass = new App();

//        assertEquals("belted        count: 1", appClass.wordCountingLogic(testAllLinesMap, "belted"));
    }

    @Test
    public void stringWithPeriodShouldReturnWithoutPeriod() throws IOException {
        App appClass = new App();
        assertEquals("Est", appClass.stringFormattingRules("Est."));
    }

/*    @Test
    public void stringWithDashShouldReturnWithoutWithHyphens() throws IOException {
        App appClass = new App();
        assertEquals("Est", appClass.stringFormattingRules("Est."));
    }*/

}
