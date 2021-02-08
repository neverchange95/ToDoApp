package com.application;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author neverchange95
 * @version 08/02/21
 * @see DateHandler
 *
 * This is a JUnit-4 test class for the DateHandler
 */

public class JUnitTestDateHandler {
    /**
     * Testing the correct translating of the method getMonthInWords()
     */
    @Test
    public void testGetMonthInWords() {
        String[] monthInNumber = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] monthInString = {"Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember"};
        for(int i = 0; i < monthInNumber.length; i++) {
            assertEquals(monthInString[i],DateHandler.getMonthInWords(monthInNumber[i]));
        }
        assertEquals("Undefinded Month!", DateHandler.getMonthInWords("14"));
        assertEquals("Input is not only numeric!", DateHandler.getMonthInWords("14abc"));
    }

    /**
     * Testing the correct translating of the method getDayInGerman()
     */
    @Test
    public void testGetDayInGerman() {
        // TODO: write a test method!
    }


}
