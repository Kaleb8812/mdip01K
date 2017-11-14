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
    public void stringWithAllNumbersShouldReturnEmptyString() throws IOException {
        App appClass = new App();
        assertEquals("", appClass.characterDeleteRules("10000"));
    }

    @Test
    public void stringWithNumbersAndLettersShouldReturnStringWithOnlyLetterCharacters() throws IOException {
        App appClass = new App();
        assertEquals("Fig", appClass.characterDeleteRules("Fig1"));
    }

    @Test
    public void stringWithCommaShouldReturnWithoutComma() throws IOException {
        App appClass = new App();
        assertEquals("said", appClass.characterDeleteRules("said,"));
    }

    @Test
    public void stringWithColonShouldReturnWithoutColon() throws IOException {
        App appClass = new App();
        assertEquals("following", appClass.characterDeleteRules("following:"));
    }

    @Test
    public void stringWithSemiColonShouldReturnWithoutSemiColon() throws IOException {
        App appClass = new App();
        assertEquals("final", appClass.characterDeleteRules("final;"));
    }

    @Test
    public void stringWithPeriodShouldReturnWithoutPeriod() throws IOException {
        App appClass = new App();
        assertEquals("Est", appClass.characterDeleteRules("Est."));
    }

    @Test
    public void stringWithQuestionMarkShouldReturnWithoutQuestionMark() throws IOException {
        App appClass = new App();
        assertEquals("going", appClass.characterDeleteRules("going?"));
    }

    @Test
    public void stringWithDashShouldReturnStringWithSpace() throws IOException {
        App appClass = new App();
        String[] stringSpaceResult = appClass.stringSpaceRules("A book-line that contains a dash!");

        assertEquals("A", stringSpaceResult[0]);
        assertEquals("book", stringSpaceResult[1]);
        assertEquals("line", stringSpaceResult[2]);
        assertEquals("that", stringSpaceResult[3]);
        assertEquals("contains", stringSpaceResult[4]);
        assertEquals("a", stringSpaceResult[5]);
        assertEquals("dash!", stringSpaceResult[6]);
    }

}
