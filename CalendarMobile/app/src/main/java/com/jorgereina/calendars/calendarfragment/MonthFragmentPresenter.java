package com.jorgereina.calendars.calendarfragment;

import android.util.Log;
import android.widget.Toast;

import com.jorgereina.calendars.CalendarApi;
import com.jorgereina.calendars.R;
import com.jorgereina.calendars.calendarfragment.MonthFragmentPresenterContract.View;
import com.jorgereina.calendars.dayfragment.DayFragment;
import com.jorgereina.calendars.MainActivity;
import com.jorgereina.calendars.model.Event;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by jorgereina on 6/25/18.
 */

public class MonthFragmentPresenter implements MonthFragmentPresenterContract.Presenter {

    private static final String TAG = MainActivity.class.getSimpleName() + "lagarto";

    private View view;
    private List<Event> events = new ArrayList<>();

    public MonthFragmentPresenter(View view, List<Event> events) {
        this.view = view;
        this.events = events;
    }

    private void getEventsRequest() {
        CalendarApi service = RetrofitInstance.getRetrofitInstance().create(CalendarApi.class);
        Call<List<Event>> call = service.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, retrofit2.Response<List<Event>> response) {
                Log.d(TAG, "onResponse: " + "month fragment");
                view.hideProgress();
                events.clear();
                events.addAll(response.body());
                view.loadEventData();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                view.fetchEventError(t.getMessage());
            }
        });
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
}
