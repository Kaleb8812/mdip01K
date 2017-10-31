package com.kaleb.allies;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        Boolean listNotEmpty = appClass.readBookAndStopWords();
        assertTrue(!listNotEmpty);
    }

}
