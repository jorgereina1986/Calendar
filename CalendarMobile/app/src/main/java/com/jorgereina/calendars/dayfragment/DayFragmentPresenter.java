package com.jorgereina.calendars.dayfragment;

import com.jorgereina.calendars.CalendarApi;
import com.jorgereina.calendars.calendarfragment.RetrofitInstance;
import com.jorgereina.calendars.model.Event;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorgereina on 6/26/18.
 */

public class DayFragmentPresenter implements DayPresenterContract.Presenter {

    private DayPresenterContract.View view;
    private List<Event> events = new ArrayList<>();
    private List<Event> dailyEvents = new ArrayList<>();


    public DayFragmentPresenter(DayPresenterContract.View view, List<Event> events) {
        this.view = view;
        this.events = events;
    }

    private void getSingleDayEvents(int day) {
        for (Event event : events) {
            if (event.getDate() != null) {
                if (Integer.parseInt(event.getDate()) == day) {
                    dailyEvents.add(event);
                }
            }
        }
        view.loadDailyEventsToRecyclerView();
    }

    @Override
    public void onViewInitialized(int day) {
        getSingleDayEvents(day);
    }

    @Override
    public void onCreateEventSelected(String title, String date, String description, String time) {

        CalendarApi service = RetrofitInstance.getRetrofitInstance().create(CalendarApi.class);
        Call<Event> call = service.postEvent(title, date, description, time);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                view.eventCreatedSuccess();
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                view.eventFailedToCreate();
            }
        });

    }

    @Override
    public int onGetDailyEventsCount() {
        return dailyEvents.size();
    }

    @Override
    public Event onGetEventData(int position) {
        return dailyEvents.get(position);
    }


}
