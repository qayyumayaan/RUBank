package rubank;

import java.util.Calendar;

/**
 * Authors: Ayaan and Mychal
 */

public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MONTHS_IN_YEAR = 12;
    public static final int FEB_LEAP_YEAR_DAYS = 29;

    public static final int AGE_SIXTEEN = 16;
    public static final int AGE_TWENTY_FOUR = 24;
    public static final int[] DAYS_IN_MONTH = {
            0, // placeholder
            31, // January
            28, // February
            31, // March
            30, // April
            31, // May
            30, // June
            31, // July
            31, // August
            30, // September
            31, // October
            30, // November
            31  // December
    };

    public Date(String dateStr) {
        String[] parts = dateStr.split("/");
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        if (month <= 0 || month > MONTHS_IN_YEAR || day <= 0) return false;

        if (month == DAYS_IN_MONTH[2]) {
            if (isLeapYear()) {
                return day <= FEB_LEAP_YEAR_DAYS;
            } else {
                return day <= DAYS_IN_MONTH[2];
            }
        } else {
            return day <= DAYS_IN_MONTH[month];
        }
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    public boolean isWithinSixMonths() {
        int within = 6;
        Calendar today = Calendar.getInstance();
        Calendar sixMonthsLater = Calendar.getInstance();
        sixMonthsLater.add(Calendar.MONTH, within);

        Calendar eventDate = Calendar.getInstance();
        eventDate.set(year, month - 1, day);

        return eventDate.compareTo(today) >= 0 && eventDate.compareTo(sixMonthsLater) <= 0;

    }

    public boolean isOlderThanSixteen() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR, -AGE_SIXTEEN);
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(year, month - 1, day);
        return eventDate.compareTo(today) < 0;
    }

    public boolean isYoungerThanTwentyFour() {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR, -AGE_TWENTY_FOUR);
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(year, month - 1, day);
        return eventDate.compareTo(today) > 0;
    }

    public boolean isTodayOrFuture() {
        Calendar today = Calendar.getInstance();
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(year, month - 1, day);
        return eventDate.compareTo(today) >= 0;
    }



    public static void main(String[] args) {
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
        testMonth_OutOfRange();
    }
    private static void testDaysInFeb_NonLeap() {
        Date day = new Date("2/29/2011");
        boolean expectedOutput = false;
        boolean actualOutput = day.isValid();
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28");
        testResult(day, expectedOutput, actualOutput);
    }

    private static void testDaysInFeb_Leap() {
        Date day = new Date("2/29/2000");
        boolean expectedOutput = true;
        boolean actualOutput = day.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a quatercentennial leap year is 29");
        testResult(day, expectedOutput, actualOutput);
    }

    private static void testMonth_OutOfRange() {
        Date day = new Date("13/31/2023");
        boolean expectedOutput = false;
        boolean actualOutput = day.isValid();
        System.out.println("**Test case #3: February 30th isn't real. ");
        testResult(day, expectedOutput, actualOutput);
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }


    private static void testResult(Date day, boolean expectedOutput, boolean actualOutput) {
        System.out.println("Testing Date: " + day);
        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);
        System.out.println(expectedOutput == actualOutput ? "Test Passed!" : "Test Failed!");
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

}
