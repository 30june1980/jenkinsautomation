package com.clairvoyant.GenericUtils;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    /*
    returns current date in required format
     */
    public static String currentDate(String format){
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(format);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String date  = zonedDateTime.format(datetimeFormatter);
        return date;

    }
}
