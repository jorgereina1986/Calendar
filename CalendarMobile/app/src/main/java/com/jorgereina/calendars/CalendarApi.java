package com.jorgereina.calendars;

import com.jorgereina.calendars.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jorgereina on 6/22/18.
 */

public interface CalendarApi {

    //https://spotical.herokuapp.com/events
    @GET("events")
    Call<List<Event>> getEvents();

    @FormUrlEncoded
    @POST("events")
    Call<Event> postEvent(@Field("title") String title,
                                @Field("date") String date,
                                @Field("description") String description,
                                @Field("time") String time);

    @PUT("events/{id}")
    Call<List<Event>> editEvent(@Path("id") String id);

    @DELETE("events/{id}")
    Call<List<Event>> deleteEvent(@Path("id") String id);


}
