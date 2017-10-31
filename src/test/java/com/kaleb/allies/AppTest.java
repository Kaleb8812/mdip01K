package com.kaleb.allies;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

public class AppTest extends TestCase {
    public AppTest(String testName) {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    //Test fails if read in List is empty
    public void testApp() throws IOException {
        App appClass = new App();
        Boolean listNotEmpty = appClass.ReadBookAndStopWords();

        assertTrue(!listNotEmpty);
    }

}
