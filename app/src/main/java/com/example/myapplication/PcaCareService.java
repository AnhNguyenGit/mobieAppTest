package com.example.myapplication;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PcaCareService {
    @GET("api/callcenter")
    Call<List<String>>  listData ();
    @POST("api/v1/fcm/register-info")
    Call<ResponseBody> postRegistrationId(@Body FirebaseRegistrationInfo data, @Query("access_token") String access_token);
    @POST("api/v1/login")
    Call<ResponseBody> login (@Body Object data);

}
