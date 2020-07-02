package com.alarmmanagerdemoapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alarmmanagerdemoapp.utils_helper.FilesUtil;

import java.io.File;
import java.io.IOException;

public class SaveLogFileApp extends Application {
    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        SaveLogFileApp.context = context;
    }

    static Context context;

    public static String getCurrentLogFile() {
        return currentLogFile;
    }

    public static void setCurrentLogFile(String currentLogFile) {
        SaveLogFileApp.currentLogFile = currentLogFile;
    }

    static String currentLogFile;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initializeLogIntoFile();
    }

    private void initializeLogIntoFile() {

           // File appDirectory = new File(Environment.getExternalStorageDirectory() +"/"+ this.getClass().getName());
               File logDirectory = FilesUtil.getLogFileDirectory();
            File logFile = new File(logDirectory, "logcat" + System.currentTimeMillis() + ".txt");
        currentLogFile = logFile.getAbsolutePath();
        Log.i("initializeLogIntoFile: ",logFile.getAbsolutePath());

            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + logFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
