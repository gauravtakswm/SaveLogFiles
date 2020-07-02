package com.alarmmanagerdemoapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alarmmanagerdemoapp.SaveLogFileApp;
import com.alarmmanagerdemoapp.utils_helper.Utils;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context, "Boot Completed !!!!!!!!!!", Toast.LENGTH_LONG).show();
        Utils.setRecurringTasks(SaveLogFileApp.getContext());
    }



}
