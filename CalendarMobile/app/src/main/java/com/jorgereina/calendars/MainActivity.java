package com.jorgereina.calendars;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jorgereina.calendars.calendarfragment.MonthFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }
        if (findViewById(R.id.fragment_container) != null) {
            MonthFragment monthFragment = new MonthFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, monthFragment)
                    .commit();
        }


    }
}
