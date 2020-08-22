package com.example.myapplication;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class ApiCall  {
    private static final String TAG = "ApiCall";
    public  ApiCall(){

    }
    public  static  List<String> resData;
    public  void CallCenterApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.pca.net.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PcaCareService servicecare = retrofit.create(PcaCareService.class);


        servicecare.listData()
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        List<String> data  =  response.body();
                       Integer count = data.size();
                       resData = data;
                       Log.d(TAG,"API called");
                       Log.d(TAG, count.toString());
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Log.d(TAG,"get callcenter data fail");
                    }
                });

        List<String> Data = resData;

    }
    public  void PostTokenToServer(String token, String accesst_token){
        FirebaseRegistrationInfo info = new FirebaseRegistrationInfo();
        info.setAppId("AppId 1");
        info.setDeviceInfo("DeviceId");
        info.setRegistration_id(token);
        info.setUsername("User03");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.pca.net.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PcaCareService servicecare = retrofit.create(PcaCareService.class);

        Map<String, String> params =  new HashMap<String, String>();
        params.put("access_token","opDXlrL7j5kRi9ZmMr95rzYDnkSAFwqEEX823MyedgdbR_2_QbGVL3qa7qF6jZb72-lVO0FIESgvG01KgPzHUcjrW8dgxaqE-GAt2nEOhC-Xq2htx97a_DS8k41aruxOBYthy8BI0HoAfNWrYLulJveCJqicIiZw_1jjU7OCjrEToyPps_6KjJXhleVC6PHCWZcWLhGt-YId2B99G2Oj0Ei2KFXLnyFPn1M0QTxHqQNPquxCt9lthbI1gqFiX-x8oeF7uW1UgVE6wUVMpc13l2buW2OAar1u_-5BBTBRy_XfpNerb1HyJH1bgGwoNKBXZ0BDdx4rWIJ11BXPqp6py1C79scwvIC5pmVtIN07CWV3PKZcUjYuLRXo5Nz_XK6b5t9vLVmFBoXxktnnSxtzbxPR0Ue7trur8hqiYttD-Uyro3eqmHLAovn7AwhbyCDjcP2VtYerYnu5LqD6KTYz8pYWduaiACnzOqoQWZHxzBWuG5a-Sgwhn25_qSxlvns4");
        servicecare.postRegistrationId(info,params)
               .enqueue(new Callback<ResponseBody>() {
                   @Override
                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       ResponseBody res = response.body();
                       String data = response.body().toString();
                   }

                   @Override
                   public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG,"Post data fail");
                   }
               });

    }

    public void Login(String username, String password){
        final Context context;
        JsonObject data = new JsonObject();
        data.addProperty("username", username);
        data.addProperty("password", password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.pca.net.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PcaCareService servicecare = retrofit.create(PcaCareService.class);
        try {
            Response<ResponseBody> res  =  servicecare.login(data).execute();
            String jsonstr = new Gson().toJson(res.body().string());
            JsonObject obj = new JsonObject().getAsJsonObject(jsonstr);
        }catch (Exception e){
                Log.d("Loi", e.getMessage());
        }


         servicecare.login(data)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Object body = response.body();
                        Object content  = response.body();
                        //JsonObject data = new JsonObject().get( response.body().toString()).getAsJsonObject();
                        String result;
                        try {

                             result = new Gson().toJson(response.body().string());
                             String s = result;
                        }
                        catch (Exception e){
                            Log.d("Loi", e.getMessage());
                        }

                        }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG,"Post data fail");
                    }
                });
    }


}
