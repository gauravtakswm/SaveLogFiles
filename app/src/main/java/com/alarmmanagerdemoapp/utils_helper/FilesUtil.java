package com.alarmmanagerdemoapp.utils_helper;

import android.content.Context;
import android.util.Log;

import com.alarmmanagerdemoapp.SaveLogFileApp;

import java.io.File;

public class FilesUtil {
    public static File getLogFileDirectory()
    {
        /*   No need of storage permission for log file, because we are storing log files into app folder
         */
        Context context = SaveLogFileApp.getContext();
        File appDirectory = new File(context.getFilesDir().getAbsolutePath() +"/log_dir");
        Log.i( "saveLogIntoFile: ",appDirectory.getAbsolutePath().toString());
        File logDirectory = new File(appDirectory + "/log");
        if (!appDirectory.exists()) {
            appDirectory.mkdir();
        }

        // create log folder
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }

        return logDirectory;

    }
}
