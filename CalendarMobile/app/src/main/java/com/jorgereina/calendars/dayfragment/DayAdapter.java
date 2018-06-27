package com.jorgereina.calendars.dayfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jorgereina.calendars.databinding.EventItemBinding;
import com.jorgereina.calendars.model.Event;

/**
 * Created by jorgereina on 6/26/18.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    private DayPresenterContract.Presenter presenter;

    public DayAdapter(DayPresenterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventItemBinding binding = EventItemBinding.inflate(inflater, parent, false);
        return new DayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {

        Event event = presenter.onGetEventData(position);
        holder.binding.eventTitleTv.setText(event.getTitle());
        holder.binding.dayTv.setText(event.getDate());
        holder.binding.timeTv.setText(event.getTime());

    }

    @Override
    public int getItemCount() {
        return presenter.onGetDailyEventsCount();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {

        private EventItemBinding binding;

        public DayViewHolder(EventItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
