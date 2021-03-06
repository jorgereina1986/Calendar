package com.jorgereina.calendars.dayfragment;

import com.jorgereina.calendars.model.Event;

/**
 * Created by jorgereina on 6/26/18.
 */

public interface DayPresenterContract {

    interface View {

        void showProgress();

        void hideProgress();

        void loadDailyEventsToRecyclerView();

        void eventCreatedSuccess();

        void eventFailedToCreate();

        void showCreateEventDialog();
    }

    interface Presenter {

        void onViewInitialized(int day);

        void onCreateEventSelected(String title, String date, String description, String time);

        int onGetDailyEventsCount();

        Event onGetEventData(int position);

        String convertTime(int hour, int minute);

        String formatDate(String date, String resId);
    }
}
