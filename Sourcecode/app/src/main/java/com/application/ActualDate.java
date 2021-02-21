package com.application;

public class ActualDate {
    private String year;
    private String month;
    private String day;

    public ActualDate(String y, String m, String d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public String getYear() {
        return this.year;
    }

    public String getMonth() {
        return this.month;
    }

    public String getDay() {
        return this.day;
    }
}
