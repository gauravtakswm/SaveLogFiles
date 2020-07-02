package com.alarmmanagerdemoapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alarmmanagerdemoapp.R;
import com.alarmmanagerdemoapp.utils_helper.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setRecurringTasks(this);
    }


}
