package com.jorgereina.calendars.util;

import com.jorgereina.calendars.model.Event;

import java.util.Comparator;

/**
 * Created by jorgereina on 6/27/18.
 */

public class DayComparator implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {

        final Integer date1 = getDay(e1.getDate());
        final Integer date2 = getDay(e2.getDate());
        Integer time1 = getTime(e1.getTime());
        Integer time2 = getTime(e2.getTime());

        Integer dayComp = (date1 == null) ? (date2 == null) ? 0 : -1 : (date2 == null) ? 1 : date1.compareTo(date2);

        if (dayComp != 0) {
            return dayComp;
        } else {
            if (time1 == null || time2 == null) {
                return 0;
            }

            return time1.compareTo(time2);
        }
    }

    private Integer getDay(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer getTime(String number) {

        number = number.replace(":", "");
        number = number.replace("AM", "");
        number = number.replace("PM", "");
        number = number.trim();

        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
