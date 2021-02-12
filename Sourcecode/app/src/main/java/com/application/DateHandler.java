package com.application;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author neverchange95
 * @version 08/02/21
 *
 * This class handles the different date-formats. It translate the english return values from the system to german values.
 */

public class DateHandler {
    private static DateHandler instance; // Save the instance of the DateHandler
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

    /**
     * @return DateHandler: An instance of the calendar object. Is a singleton pattern
     */
    static public DateHandler getInstance() {
        if(instance == null) {
            instance = new DateHandler();
        }
        return instance;
    }

    /**
     * The constructor DateHandler calculates the different String values for the actual dayS
     */
    private DateHandler() {
        SimpleDateFormat d = new SimpleDateFormat("EEEE, dd/MM/yyyy"); // set a format to get the actual day
        SimpleDateFormat d2 = new SimpleDateFormat("EEEE"); // set a format to only get the actual day in words
        String date = d.format(Calendar.getInstance().getTime()); // returns the actual day in this format: 08/02/2021

        this.dayInEng = d2.format(Calendar.getInstance().getTime()); // get the actual day in format d2: example(Monday)
        this.day = getDayFormatted(dayInEng) + date.substring(date.indexOf(' ')+1, date.indexOf('/')); // set variable day to: Mo. 08
        this.month = getMonthInWords(date.substring(date.indexOf('/')+1, date.lastIndexOf('/'))); // set variable month to: Februar
        this.year = Integer.parseInt(date.substring(date.lastIndexOf('/')+1)); // set variable year to: 2021

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year); // Set the year
        calendar.set(Calendar.MONTH,monthInt); // Set the Month beginning by 0 (January = 0, December = 11)
        numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Get number of days in choosed year and month
        calendar.set(Calendar.DAY_OF_MONTH,1); // Set here the first day of month, it´s important because otherwise the calendar calculate the first day from actual day
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
    public static String getDayFormatted(String day) {
        System.out.println(day);
        if("Monday".equals(day) || "Montag".equals(day)) {
            return "Mo. ";
        } else if("Tuesday".equals(day) || "Dienstag".equals(day)) {
            return "Di. ";
        } else if("Wednesday".equals(day) || "Mittwoch".equals(day)) {
            return "Mi. ";
        } else if("Thursday".equals(day) || "Donnerstag".equals(day)) {
            return "Do. ";
        } else if("Friday".equals(day) || "Freitag".equals(day)) {
            return "Fr. ";
        } else if("Saturday".equals(day) || "Samstag".equals(day)) {
            return "Sa. ";
        } else if("Sunday".equals(day) || "Sonntag".equals(day)) {
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

    // Getters
    /**
     * @return year as a String: 2021
     */
    public String getYear() {
        return this.year+"";
    }

    /**
     * @return month as a String: Februar
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * @return day as a String: Di. 09
     */
    public String getDay() {
        return this.day;
    }


    // Setters
    /**
     * @param year as a String: 2021
     */
    public void setYear(String year) {
        int y = Integer.parseInt(year);
        this.year = y;
    }

    /**
     * @param month as a String: Februar
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @param day as a String: Di. 09
     */
    public void setDay(String day) {
        this.day = day;
    }

}
