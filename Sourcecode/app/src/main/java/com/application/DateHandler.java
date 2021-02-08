package com.application;

import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author neverchange95
 * @version 08/02/21
 * @see DateHolder this class holds only one object of this class
 *
 * This class handles the different date-formats. It translate the english return values from the system to german values.
 */

public class DateHandler {
//    private CalendarView cv;
    private int year;
    private String month;
    private String day;
    private String dayInEng;
    private int numberOfDays;
    private static int monthInt;
    private int fistDayInMonth;



//    public DateHandler(CalendarView calendarView) {
//        this.cv = calendarView;
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat d2 = new SimpleDateFormat("EEE");
//        dayInWords = d2.format(new Date(cv.getDate()));
//
//        String date = df.format(new Date(cv.getDate()));
//        this.year = date.substring(0,date.indexOf('/'));
//        this.month = date.substring(date.indexOf('/')+1, date.lastIndexOf('/'));
//        this.day = date.substring(date.lastIndexOf('/')+1);
//    }

    public DateHandler() {
        SimpleDateFormat d = new SimpleDateFormat("EEEE, dd/MM/yyyy"); // set a format to get the actual day
        SimpleDateFormat d2 = new SimpleDateFormat("EEEE"); // set a format to only get the actual day in words
        String date = d.format(Calendar.getInstance().getTime()); // returns the actual day in this format: 08/02/2021

        this.dayInEng = d2.format(Calendar.getInstance().getTime()); // get the actual day in format d2: example(Monday)
        this.day = getDayInGerman(dayInEng) + date.substring(date.indexOf(' ')+1, date.indexOf('/')); // set variable day to: Mo. 08
        this.month = getMonthInWords(date.substring(date.indexOf('/')+1, date.lastIndexOf('/'))); // set variable month to: Februar
        this.year = Integer.parseInt(date.substring(date.lastIndexOf('/')+1)); // set variable year to: 2021

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.YEAR,year); // Set the year
        calendar.set(Calendar.MONTH,monthInt); // Set the Month beginning by 0 (January = 0, December = 11)
        numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Get number of days in choosed year and month
        fistDayInMonth = calendar.get(Calendar.DAY_OF_WEEK); // Get the first day in the month as int MO=2, DI=3, MI=4, DO=5, FR=6, SA=7, SO=1


        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("Der Februar hat " + numberOfDays + " Tage!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    /**
     * @return days[]: returns all days in the actual month formatted like Mo. 01
     */

    public String[] getAllDaysFormatted() {
        String[] days = new String[numberOfDays]; // return array
        String[] dayIndex = {"So. ", "Mo. ", "Di. ", "Mi. ", "Do. ", "Fr. ", "Sa. "}; // values for index position
        int indexCounter = fistDayInMonth-1; // for calculating the index position of the array dayIndex
        for(int i = 0; i < numberOfDays; i++) {
            if(i+1 < 10) {
                days[i] = dayIndex[indexCounter] + "0"+(i+1);
            } else {
                days[i] = dayIndex[indexCounter] + (i+1);
            }
            indexCounter++;
            if(indexCounter == 7) {
                indexCounter = 0;
            }
        }
        return days;
    }

    /**
     * @param  day: is the actual day in this week. The value goes from Monday to Sunday
     * @return day: is the actual day in this week but now in german and shorter. Returns Mo. to So.
     */
    public static String getDayInGerman(String day) {
        if("Monday".equals(day)) {
            return "Mo. ";
        } else if("Thuesday".equals(day)) {
            return "Di. ";
        } else if("Wednesday".equals(day)) {
            return "Mi. ";
        } else if("Thursday".equals(day)) {
            return "Do. ";
        } else if("Friday".equals(day)) {
            return "Fr. ";
        } else if("Saturday".equals(day)) {
            return "Sa. ";
        } else if("Sunday".equals(day)) {
            return "So. ";
        } else {
            return "Undefined Day!";
        }
    }

    /**
     * @param month: is the actual month of this year. The value goes from 01 to 12
     * @return month: is the actual month of this year but now in german and not numeric. Returns Januar to Dezember
     * The static variable monthInt is to get the number of days from the Class Calendar or GregorianCalendar.
     */
    public static String getMonthInWords(String month) {
        if(month.matches("[0-9]{2}")) {
            int m = Integer.parseInt(month);
            if(m == 1) {
                monthInt = m-1;
                return "Januar";
            } else if(m == 2) {
                monthInt = m-1;
                return "Februar";
            } else if(m == 3) {
                monthInt = m-1;
                return "März";
            } else if(m == 4) {
                monthInt = m-1;
                return "April";
            } else if(m == 5) {
                monthInt = m-1;
                return "Mai";
            } else if(m == 6) {
                monthInt = m-1;
                return "Juni";
            } else if(m == 7) {
                monthInt = m-1;
                return "Juli";
            } else if(m == 8) {
                monthInt = m-1;
                return "August";
            } else if(m == 9) {
                monthInt = m-1;
                return "September";
            } else if(m == 10) {
                monthInt = m-1;
                return "Oktober";
            } else if(m == 11) {
                monthInt = m-1;
                return "November";
            } else if(m == 12) {
                monthInt = m-1;
                return "Dezember";
            } else {
                return "Undefined Month!";
            }
        } else {
            return "Input is not only numeric!";
        }
    }



    /**
     * @return year as a String
     */
    public String getYear() {
        return this.year+"";
    }

    /**
     * @return month as a String
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * @return day as a String
     */
    public String getDay() {
        return this.day;
    }

}
