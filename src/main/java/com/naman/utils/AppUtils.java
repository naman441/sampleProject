package com.naman.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {
	
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static String formatDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return formatter.format(date);
    }

}
