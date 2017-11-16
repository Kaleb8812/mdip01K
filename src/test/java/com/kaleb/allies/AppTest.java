package com.kaleb.allies;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void basicRunAppMethod() throws IOException {
        App appClass = new App();
        System.out.println("Top 100 Most Frequent Word List:");
        System.out.println("WORD|COUNT");
        ArrayList resultWordList = appClass.readBookAndStopWords("mobydick", 100, "|");
        for (Object aResultWordList : resultWordList) {
            System.out.println(aResultWordList);
        }
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
        App appClass = new App();
        ArrayList testSortedWordList = appClass.readBookAndStopWords("frequency-test", 2, "|");

        assertEquals(2, testSortedWordList.size());
        assertTrue(testSortedWordList.contains("methinks|4"));
        assertTrue(testSortedWordList.contains("dead|4"));
    }

    @Test
    public void stopWordFormattingShouldReturnAsListOfLowercaseStrings() throws IOException {
        List myList = new ArrayList();
        myList.add("#The");
        myList.add("");
        myList.add("#");
        myList.add("The");
        myList.add("the");
        myList.add("Im");
        myList.add("");
        myList.add("THE");
        myList.add("end");
        myList.add("");

        App appClass = new App();
        List testStopWordFormattedList = appClass.stopWordListFormatting(myList);

        assertEquals("the", testStopWordFormattedList.get(0));
        assertEquals("the", testStopWordFormattedList.get(1));
        assertEquals("im", testStopWordFormattedList.get(2));
        assertEquals("the", testStopWordFormattedList.get(3));
        assertEquals("end", testStopWordFormattedList.get(4));
    }

    @Test
    public void stringsWithNonAlphaCharactersShouldReturnStringsWithRemovedNonAlphaCharacters() throws IOException {
        App appClass = new App();

        assertEquals("", appClass.characterDeleteRules("10000"));
        assertEquals("Fig", appClass.characterDeleteRules("Fig1"));
        assertEquals("said", appClass.characterDeleteRules("said,"));
        assertEquals("following", appClass.characterDeleteRules("following:"));
        assertEquals("final", appClass.characterDeleteRules("final;"));
        assertEquals("Est", appClass.characterDeleteRules("Est."));
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

    @Test
    public void stringWithLongDashShouldReturnStringWithSpace() throws IOException {
        App appClass = new App();
        String[] stringSpaceResult = appClass.stringSpaceRules("A book—line that contains a long dash.");

        assertEquals("A", stringSpaceResult[0]);
        assertEquals("book", stringSpaceResult[1]);
        assertEquals("line", stringSpaceResult[2]);
        assertEquals("that", stringSpaceResult[3]);
        assertEquals("contains", stringSpaceResult[4]);
        assertEquals("a", stringSpaceResult[5]);
        assertEquals("long", stringSpaceResult[6]);
        assertEquals("dash.", stringSpaceResult[7]);
    }
}
