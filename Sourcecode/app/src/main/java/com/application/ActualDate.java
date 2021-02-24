package com.application;

/**
 * @author neverchange95
 * @version 02/2021
 *
 * This Class is only for saving and getting the actual date
 */
public class ActualDate {
    private String year;
    private String month;
    private String day;

    /**
     * Constructor which is setting the attributes with the elements of the actual day
     * @see DateHandler
     * @param y Contains the actual year
     * @param m Contains the actual month
     * @param d Contains the actual day
     */
    public ActualDate(String y, String m, String d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    /**
     * @return The actual year
     */
    public String getYear() {
        return this.year;
    }

    /**
     * @return The actual month
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * @return The actual day
     */
    public String getDay() {
        return this.day;
    }
}
