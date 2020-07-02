


package com.alarmmanagerdemoapp.network_call;


import com.alarmmanagerdemoapp.model_classes.LogFileUploadResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {


    @Multipart
    @POST("log/multiple_files_upload")
    Call<LogFileUploadResponse> uploadMultipleFiles(@Part List<MultipartBody.Part> file);

}
