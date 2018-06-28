package com.jorgereina.calendars.calendarfragment;

import com.jorgereina.calendars.model.Event;

/**
 * Created by jorgereina on 6/25/18.
 */

public interface MonthFragmentPresenterContract {

    interface View {

        void loadEventData();

        void showProgress();

        void hideProgress();

        void showDayDetails(int day);

        void fetchEventError(String message);

        void showEditDeleteEventDialog(Event event);

        void makeToast(int message);
    }

    interface Presenter {

        void onDaySelectedClicked(int day);

        int onGetEventsCount();

        Event onGetEventData(int position);

        void onViewInitialized();

        String convertTime(String time);

        String formatDate(String date, String resId);

        void eventSelected(int position);

        void onDeleteEventSelected(Event event);

        void onEditEventSelected(Event event, String title, String description, String time);
    }

}
