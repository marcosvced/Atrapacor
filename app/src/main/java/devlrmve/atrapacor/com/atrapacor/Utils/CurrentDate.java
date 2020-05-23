package devlrmve.atrapacor.com.atrapacor.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Marco on 03/01/2016.
 */
public class CurrentDate {

    //method that returns the current date.
    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:mm");

        Calendar c = Calendar.getInstance();
        String date = dateFormat.format(c.getTime());
        return date;
    }

    //method that returns a String of a date
    public static String getDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:mm");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    //method that returns a Date of a String
    public static Date dateFormat(String string_date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(string_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //method that returns the date un dd/MM/yyyy format
    public static String dateFormatString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date_string = dateFormat.format(date);
        return date_string;
    }

    //method that returns the hour of a date
    public static String getHourString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String hour_string = dateFormat.format(date);
        return hour_string;
    }

    //method that returns true if the date is today.
    public static boolean isToday(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date_string = dateFormat.format(date);

        Calendar c = Calendar.getInstance();
        String today = dateFormat.format(c.getTime());

        if (date_string.equalsIgnoreCase(today)) {
            return true;
        }
        return false;
    }

    //method that returs true if the date is yesterday.
    public static boolean isYesterday(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date_string = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(c.getTime());

        if (date_string.equalsIgnoreCase(yesterday)) {
            return true;
        }
        return false;
    }
}
