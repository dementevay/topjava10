package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Comparator;

/**
 * GKislin
 * 07.01.2015.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetweenDate(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
    }

    /*public static <T extends Temporal> T isBetween(Class<T> obj, Class<T> obj1, Class<T> obj2){
        boolean b = obj.compareTo(obj1) >= 0 && obj.compareTo(obj2) <= 0;
        return null;
    }

    Comparator<Object> compare = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }
    };*/

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
