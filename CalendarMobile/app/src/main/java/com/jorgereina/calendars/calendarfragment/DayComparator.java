package com.jorgereina.calendars.calendarfragment;

import android.text.TextUtils;

import com.jorgereina.calendars.model.Event;

import java.util.Comparator;

/**
 * Created by jorgereina on 6/27/18.
 */

public class DayComparator implements Comparator<Event> {


    @Override
    public int compare(Event e1, Event e2) {

        final Integer date1 = getInt(e1.getDate());
        final Integer date2 = getInt(e2.getDate());
        Integer time1 = getInt(e1.getTime());
        Integer time2 = getInt(e2.getTime());

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


    private Integer getInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
