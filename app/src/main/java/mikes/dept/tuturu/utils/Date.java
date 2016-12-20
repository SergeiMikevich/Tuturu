package mikes.dept.tuturu.utils;

import java.util.Calendar;

/**
 * Created by mikes on 17.12.16.
 */

public class Date {

    public static String formatDateToString(int year, int month, int dayOfMonth){
        return dayOfMonth + " " + getMonthString(month) + " " + year;
    }

    private static String getMonthString(int month){
        switch(month){
            case Calendar.JANUARY: return "января";
            case Calendar.FEBRUARY: return "февраля";
            case Calendar.MARCH: return "марта";
            case Calendar.APRIL: return "апреля";
            case Calendar.MAY: return "мая";
            case Calendar.JUNE: return "июня";
            case Calendar.JULY: return "июля";
            case Calendar.AUGUST: return "августа";
            case Calendar.SEPTEMBER: return "сентября";
            case Calendar.OCTOBER: return "октября";
            case Calendar.NOVEMBER: return "ноября";
            case Calendar.DECEMBER: return "декабря";
            default: return "";
        }
    }

}
