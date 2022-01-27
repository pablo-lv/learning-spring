package com.pablo.lucas.learningspring.business.web;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date createDateFromDateString(final String dateString) {
        Date date = null;
        if (dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);
            } catch(ParseException pe) {
                date = new Date();
            }
        } else {
            date = new Date();
        }

        return date;
    
    }


}
