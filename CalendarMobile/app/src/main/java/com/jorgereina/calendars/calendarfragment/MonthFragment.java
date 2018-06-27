package com.jorgereina.calendars.calendarfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jorgereina.calendars.R;
import com.jorgereina.calendars.calendarfragment.MonthFragmentPresenterContract.Presenter;
import com.jorgereina.calendars.databinding.FragmentMonthBinding;
import com.jorgereina.calendars.dayfragment.DayFragment;
import com.jorgereina.calendars.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgereina on 6/25/18.
 */

public class MonthFragment extends Fragment implements MonthFragmentPresenterContract.View, View.OnClickListener {

    private FragmentMonthBinding binding;
    private Presenter presenter;
    private List<Event> events = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private MonthAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_month, container, false);
        presenter = new MonthFragmentPresenter(this, events);
        setClickListenersToViews();
        presenter.onViewInitialized();
        adapter = new MonthAdapter(presenter);
        layoutManager = new LinearLayoutManager(getActivity());
        binding.monthEventsRv.setLayoutManager(layoutManager);
        binding.monthEventsRv.setAdapter(adapter);
        showProgress();
        return binding.getRoot();
    }

    @Override
    public void loadEventData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDayDetails(int day) {
        DayFragment fragment = DayFragment.newInstance(events, day);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void fetchEventError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.day_1:
                presenter.onDaySelectedClicked(1);
                break;
            case R.id.day_2:
                presenter.onDaySelectedClicked(2);
                break;
            case R.id.day_3:
                presenter.onDaySelectedClicked(3);
                break;
            case R.id.day_4:
                presenter.onDaySelectedClicked(4);
                break;
            case R.id.day_5:
                presenter.onDaySelectedClicked(5);
                break;
            case R.id.day_6:
                presenter.onDaySelectedClicked(6);
                break;
            case R.id.day_7:
                presenter.onDaySelectedClicked(7);
                break;
            case R.id.day_8:
                presenter.onDaySelectedClicked(8);
                break;
            case R.id.day_9:
                presenter.onDaySelectedClicked(9);
                break;
            case R.id.day_10:
                presenter.onDaySelectedClicked(10);
                break;
            case R.id.day_11:
                presenter.onDaySelectedClicked(11);
                break;
            case R.id.day_12:
                presenter.onDaySelectedClicked(12);
                break;
            case R.id.day_13:
                presenter.onDaySelectedClicked(13);
                break;
            case R.id.day_14:
                presenter.onDaySelectedClicked(14);
                break;
            case R.id.day_15:
                presenter.onDaySelectedClicked(15);
                break;
            case R.id.day_16:
                presenter.onDaySelectedClicked(16);
                break;
            case R.id.day_17:
                presenter.onDaySelectedClicked(17);
                break;
            case R.id.day_18:
                presenter.onDaySelectedClicked(18);
                break;
            case R.id.day_19:
                presenter.onDaySelectedClicked(19);
                break;
            case R.id.day_20:
                presenter.onDaySelectedClicked(20);
                break;
            case R.id.day_21:
                presenter.onDaySelectedClicked(21);
                break;
            case R.id.day_22:
                presenter.onDaySelectedClicked(22);
                break;
            case R.id.day_23:
                presenter.onDaySelectedClicked(23);
                break;
            case R.id.day_24:
                presenter.onDaySelectedClicked(24);
                break;
            case R.id.day_25:
                presenter.onDaySelectedClicked(25);
                break;
            case R.id.day_26:
                presenter.onDaySelectedClicked(26);
                break;
            case R.id.day_27:
                presenter.onDaySelectedClicked(27);
                break;
            case R.id.day_28:
                presenter.onDaySelectedClicked(28);
                break;
        }
    }

    private void setClickListenersToViews() {

        binding.day1.setOnClickListener(this);
        binding.day2.setOnClickListener(this);
        binding.day3.setOnClickListener(this);
        binding.day4.setOnClickListener(this);
        binding.day5.setOnClickListener(this);
        binding.day6.setOnClickListener(this);
        binding.day7.setOnClickListener(this);
        binding.day8.setOnClickListener(this);
        binding.day9.setOnClickListener(this);
        binding.day10.setOnClickListener(this);
        binding.day11.setOnClickListener(this);
        binding.day12.setOnClickListener(this);
        binding.day13.setOnClickListener(this);
        binding.day14.setOnClickListener(this);
        binding.day15.setOnClickListener(this);
        binding.day16.setOnClickListener(this);
        binding.day17.setOnClickListener(this);
        binding.day18.setOnClickListener(this);
        binding.day19.setOnClickListener(this);
        binding.day20.setOnClickListener(this);
        binding.day21.setOnClickListener(this);
        binding.day22.setOnClickListener(this);
        binding.day23.setOnClickListener(this);
        binding.day24.setOnClickListener(this);
        binding.day25.setOnClickListener(this);
        binding.day26.setOnClickListener(this);
        binding.day27.setOnClickListener(this);
        binding.day28.setOnClickListener(this);
    }
}
