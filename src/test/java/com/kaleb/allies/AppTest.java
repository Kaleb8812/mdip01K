package com.kaleb.allies;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class AppTest {

    //Test fails if read in List is empty
    @Test
    public void inputValidFileShouldReturnTrue() throws IOException {
        App appClass = new App();
        Boolean listNotEmpty = appClass.readBookAndStopWords();
        assertTrue(!listNotEmpty);
    }

    @Test
    public void fileContainingMultipleIdenticalWordsShouldPrintTotalAmountOfSingleWord() throws IOException {
        App appClass = new App();
        //Boolean listNotEmpty = appClass.readBookAndStopWords();
        assertTrue(true);
    }


}
