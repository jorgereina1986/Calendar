package com.jorgereina.calendars.calendarfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jorgereina.calendars.calendarfragment.MonthFragmentPresenterContract.Presenter;
import com.jorgereina.calendars.databinding.EventItemBinding;
import com.jorgereina.calendars.model.Event;

/**
 * Created by jorgereina on 6/25/18.
 */

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MonthViewHolder> {

    private Presenter presenter;

    public MonthAdapter(Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventItemBinding binding = EventItemBinding.inflate(inflater, parent, false);
        return new MonthViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, int position) {

        Event event = presenter.onGetEventData(position);
        holder.binding.eventTitleTv.setText(event.getTitle());
        holder.binding.dayTv.setText(event.getDate());
        holder.binding.timeTv.setText(event.getTime());
    }

    @Override
    public int getItemCount() {
        return presenter.onGetEventsCount();
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder {

        private EventItemBinding binding;

        public MonthViewHolder(EventItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
