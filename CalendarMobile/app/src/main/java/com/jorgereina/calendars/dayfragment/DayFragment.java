package com.jorgereina.calendars.dayfragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jorgereina.calendars.R;
import com.jorgereina.calendars.databinding.DialogCreateEventBinding;
import com.jorgereina.calendars.databinding.FragmentDayBinding;
import com.jorgereina.calendars.model.Event;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by jorgereina on 6/25/18.
 */

public class DayFragment extends Fragment implements DayPresenterContract.View {

    private static final String EVENT_LIST_PARCEL = "event_list_parcel";
    private static final String DAY_PARCEL = "day_parcel";

    private FragmentDayBinding binding;
    private DayPresenterContract.Presenter presenter;
    private DayAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false);
        events = Parcels.unwrap(this.getArguments().getParcelable(EVENT_LIST_PARCEL));
        presenter = new DayFragmentPresenter(this, events);
        adapter = new DayAdapter(presenter);
        presenter.onViewInitialized(this.getArguments().getInt(DAY_PARCEL));
        layoutManager = new LinearLayoutManager(getActivity());
        binding.dayRv.setLayoutManager(layoutManager);
        binding.dayRv.setAdapter(adapter);
        hideProgress();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fabCreatEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateEventDialog();
//                presenter.onCreateEventSelected(
//                        binding.titleEt.getText().toString(),
//                        String.valueOf(day),
//                        binding.descriptionEt.getText().toString(),
//                        binding.timeEt.getText().toString());
            }
        });
    }

    public static DayFragment newInstance(List<Event> event, int day) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EVENT_LIST_PARCEL, Parcels.wrap(event));
        bundle.putInt(DAY_PARCEL, day);
        DayFragment fragment = new DayFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showProgress() {
        binding.dayPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.dayPb.setVisibility(View.GONE);
    }

    @Override
    public void loadDailyEventsToRecyclerView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void eventCreatedSuccess() {
//        binding.titleEt.setText("");
//        binding.descriptionEt.setText("");
//        binding.timeEt.setText("");
        makeToast(R.string.event_created);
    }

    @Override
    public void eventFailedToCreate() {
        makeToast(R.string.event_not_created);
    }

    @Override
    public void showCreateEventDialog() {

        final DialogCreateEventBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(
                getActivity()),
                R.layout.dialog_create_event,
                null,
                false);

        final String title = dialogBinding.titleEt.getText().toString();
        final String description = dialogBinding.descriptionEt.getText().toString();
        final String time = dialogBinding.timeEt.getText().toString();
        final int day = this.getArguments().getInt(DAY_PARCEL);

        buildDialog(dialogBinding, title, description, time, day).show();
    }

    private AlertDialog buildDialog(final DialogCreateEventBinding dialogBinding,
                                    final String title,
                                    final String description,
                                    final String time,
                                    final int day) {

        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.hours_array,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> mintueAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.minutes_array,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> amPmAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.am_pm_array,
                android.R.layout.simple_spinner_item);

        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mintueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dialogBinding.hourSpinner.setAdapter(hourAdapter);
        dialogBinding.minuteSpinner.setAdapter(mintueAdapter);
        dialogBinding.amPmSpinner.setAdapter(amPmAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogBinding.getRoot())
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!title.isEmpty() && !time.isEmpty()) {
                            presenter.onCreateEventSelected(
                                    title,
                                    String.valueOf(day),
                                    description,
                                    time);
                        }else {
                            makeToast(R.string.missing_field);
                        }
                    }

                });
        return builder.create();
    }

    private void makeToast(int message) {
        Toast.makeText(getActivity(), getString(message), Toast.LENGTH_LONG).show();
    }


}
