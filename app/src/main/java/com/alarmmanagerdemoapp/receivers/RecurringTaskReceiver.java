package com.alarmmanagerdemoapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alarmmanagerdemoapp.SaveLogFileApp;
import com.alarmmanagerdemoapp.model_classes.LogFileUploadResponse;
import com.alarmmanagerdemoapp.network_call.APICommon;
import com.alarmmanagerdemoapp.utils_helper.FilesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/*public class RecurringTaskReceiver {

}*/
public class RecurringTaskReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        // add calculation logic here

        /*   No need of storage permission for log file, because we are storing log files into app folder
         */

        List<MultipartBody.Part> parts = new ArrayList<>();

        for (int i = 0; i < FilesUtil.getLogFileDirectory().listFiles().length; i++){
            parts.add(prepareFilePart("log_file["+i+"]", FilesUtil.getLogFileDirectory().listFiles()[i]));
           //Toast.makeText(context, "happening !!!!!!!!!!"+FilesUtil.getLogFileDirectory().listFiles()[i].getAbsolutePath(), Toast.LENGTH_LONG).show();
           // Log.i( "onReceive: ",i+" "+FilesUtil.getLogFileDirectory().listFiles()[i].getAbsolutePath());
        }
        uploadMultiPleFilesToServer(parts,FilesUtil.getLogFileDirectory().listFiles());
    }

    private void uploadMultiPleFilesToServer(final List<MultipartBody.Part> files, final File logFiles[]) {
        Call<LogFileUploadResponse> call = APICommon.getInstance().uploadMultipleFiles(files);
        call.enqueue(new retrofit2.Callback<LogFileUploadResponse>() {
            @Override
            public void onResponse(Call<LogFileUploadResponse> call, Response<LogFileUploadResponse> response)
            {
                //deleting previous log files, Not deleting current log file
                for(File file:logFiles)
                {
                    if(!file.getAbsolutePath().equalsIgnoreCase(SaveLogFileApp.getCurrentLogFile()))
                        file.delete();
                }
      //do some action on success
            }

            @Override
            public void onFailure(Call<LogFileUploadResponse> call, Throwable t) {
                //do some action on failure
            }
        });

    }


    private MultipartBody.Part prepareFilePart(String partName, File file) {
        Context context = SaveLogFileApp.getContext();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }


        }