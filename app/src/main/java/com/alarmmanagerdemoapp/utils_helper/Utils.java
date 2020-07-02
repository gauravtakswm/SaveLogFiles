package com.alarmmanagerdemoapp.utils_helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.alarmmanagerdemoapp.SaveLogFileApp;
import com.alarmmanagerdemoapp.receivers.RecurringTaskReceiver;

import java.util.Calendar;

public class Utils {
    public static void setRecurringTasks(Context context)
    {
        try{
            cancelRecurringTasks(SaveLogFileApp.getContext());
        }catch (Exception e){}
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, AppConstants.TIME_TO_UPLOAD_HOUR); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, AppConstants.TIME_TO_UPLOAD_MINUTES);
        calendar.set(Calendar.SECOND, AppConstants.TIME_TO_UPLOAD_SECONDS);

        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, RecurringTaskReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }
    public static void cancelRecurringTasks(Context context)
    {
        Intent intent = new Intent(context, RecurringTaskReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
