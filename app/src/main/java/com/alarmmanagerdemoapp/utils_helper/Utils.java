package com.alarmmanagerdemoapp.utils_helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

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
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0); //flags PendingIntent.FLAG_UPDATE_CURRENT
        if (Build.VERSION.SDK_INT >= 23)
        {
            am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pi);
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmPeriodicTime, pendingIntent);
        }
        else if (Build.VERSION.SDK_INT >= 19)
        {
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pi);
           }
        else {
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        }

        //     am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);
    }
    public static void cancelRecurringTasks(Context context)
    {
        Intent intent = new Intent(context, RecurringTaskReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
