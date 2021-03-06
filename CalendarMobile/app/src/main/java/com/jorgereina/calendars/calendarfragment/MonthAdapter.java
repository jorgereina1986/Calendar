package com.jorgereina.calendars.calendarfragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgereina.calendars.R;
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
        String resId = holder.binding.getRoot().getResources().getString(R.string.date);
        holder.binding.eventTitleTv.setText(event.getTitle());
        holder.binding.dayTv.setText(presenter.formatDate(event.getDate(), resId));
        holder.binding.timeTv.setText(event.getTime());
    }

    @Override
    public int getItemCount() {
        return presenter.onGetEventsCount();
    }

    public class MonthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private EventItemBinding binding;

        private MonthViewHolder(EventItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            presenter.eventSelected(position);
        }
    }
}
