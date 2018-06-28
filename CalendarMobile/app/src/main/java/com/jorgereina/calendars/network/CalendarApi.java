package com.jorgereina.calendars.network;

import com.jorgereina.calendars.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

//    @Multipart
//    @PUT("events/{id}")
//    Call<Event> editEvent(@Path("id") String id,
//                                @Part("title") String title,
//                                @Part("date") String date,
//                                @Part("description") String description,
//                                @Part("time") String time);

    @PUT("events/{id}")
    Call<Event> editEvent(@Path("id") String id, @Body Event event);

    @DELETE("events/{id}")
    Call<List<Event>> deleteEvent(@Path("id") String id);
}
