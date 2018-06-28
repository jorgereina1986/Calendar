package com.jorgereina.calendars.calendarfragment;

import com.jorgereina.calendars.R;
import com.jorgereina.calendars.network.CalendarApi;
import com.jorgereina.calendars.calendarfragment.MonthFragmentPresenterContract.View;
import com.jorgereina.calendars.model.Event;
import com.jorgereina.calendars.network.RetrofitInstance;
import com.jorgereina.calendars.util.DayComparator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jorgereina on 6/25/18.
 */

public class MonthFragmentPresenter implements MonthFragmentPresenterContract.Presenter {

    private View view;
    private List<Event> events = new ArrayList<>();

    public MonthFragmentPresenter(View view, List<Event> events) {
        this.view = view;
        this.events = events;
    }

    @Override
    public void onDaySelectedClicked(int day) {
        view.showDayDetails(day);
    }

    @Override
    public int onGetEventsCount() {
        return events.size();
    }

    @Override
    public Event onGetEventData(int position) {
        return events.get(position);
    }

    @Override
    public void onViewInitialized() {
        getEventsRequest();
    }

    @Override
    public String convertTime(int hour, int minute) {

        String h;
        String m;

        if (hour <= 9) {
            h = "0" + hour;
        } else {
            h = hour + "";
        }

        if (minute <= 9) {
            m = "0" + minute;
        } else {
            m = minute + "";
        }

        DateTimeFormatter inputFormatter = DateTimeFormat.forPattern("HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormat.forPattern("hh:mm a");
        DateTime dateTime = inputFormatter.parseDateTime(h+m);
        String formattedTimestamp = outputFormatter.print(dateTime.getMillis());
        return formattedTimestamp;
    }

    @Override
    public String formatDate(String date, String resId) {
        return String.format(resId, date);
    }

    @Override
    public void eventSelected(int position) {
        view.showEditDeleteEventDialog(events.get(position));
    }

    @Override
    public void onDeleteEventSelected(Event event) {
        deleteEventRequest(event);
    }

    @Override
    public void onEditEventSelected(Event event, String title, String description, String time) {
        editEventRequest(event, title, description, time);
    }

    private void getEventsRequest() {
        CalendarApi service = RetrofitInstance.getRetrofitInstance().create(CalendarApi.class);
        Call<List<Event>> call = service.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, retrofit2.Response<List<Event>> response) {
                view.hideProgress();
                events.clear();
                events.addAll(response.body());
                Collections.sort(events, new DayComparator());
                view.loadEventData();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                view.fetchEventError(t.getMessage());
            }
        });
    }

    private void deleteEventRequest(Event event) {
        CalendarApi service = RetrofitInstance.getRetrofitInstance().create(CalendarApi.class);
        Call<List<Event>> call = service.deleteEvent(event.getId());
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                view.makeToast(R.string.event_deleted);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                view.fetchEventError(t.getMessage());
            }
        });
    }

    private void editEventRequest(Event event, String title, String description, String time) {
        CalendarApi service = RetrofitInstance.getRetrofitInstance().create(CalendarApi.class);


        Call<Event> call = service.editEvent(
                event.getId(),
                new Event(event.getId(), title, event.getDate(), description, time));

        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                view.makeToast(R.string.event_edited);
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                view.fetchEventError(t.getMessage());
            }
        });
    }
}
