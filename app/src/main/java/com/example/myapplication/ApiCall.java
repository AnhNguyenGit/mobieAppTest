package com.example.myapplication;

import android.content.Context;
import android.nfc.Tag;
import android.provider.Settings;
import android.util.JsonToken;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;

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
    private String appId = "1:216004051522:android:0d314b3f21282881e363db";
    private final Context context;

    ApiCall(Context context){
        this.context = context;
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
    public  void PostTokenToServer(String access_token, String token, String userName, String DeviceId){
        FirebaseRegistrationInfo info = new FirebaseRegistrationInfo();
        info.setAppId(appId);
        info.setDeviceInfo(DeviceId);
        info.setRegistration_id(token);
        info.setUsername(userName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.pca.net.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PcaCareService servicecare = retrofit.create(PcaCareService.class);
        servicecare.postRegistrationId(info,access_token)
               .enqueue(new Callback<ResponseBody>() {
                   @Override
                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       ResponseBody res = response.body();
                       String data = response.body().toString();
                       Toast.makeText(context,"Post data to server success!", Toast.LENGTH_SHORT).show();

                   }

                   @Override
                   public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG,"Post data fail");
                        Toast.makeText(context,"Post data to server fail!", Toast.LENGTH_SHORT).show();
                   }
               });

    }

    public void Login(final String username, String password, final Context context){
        //final Context context;
        JsonObject data = new JsonObject();
        data.addProperty("username", username);
        data.addProperty("password", password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://graph.pca.net.vn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PcaCareService servicecare = retrofit.create(PcaCareService.class);



         servicecare.login(data)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Object body = response.body();
                        Object content  = response.body();
                        //JsonObject data = new JsonObject().get( response.body().toString()).getAsJsonObject();
                        String result ="No data";
                        try {
                            if(body!=null)
                            {
                                Gson gson = new Gson();
                                result = new Gson().toJson(response.body().string());
                                JsonElement element = new JsonParser().parse(result);
                                 String jContent = element.getAsString();
                                 JsonObject resObj = gson.fromJson(jContent, JsonObject.class);
                                String access_token = resObj.get("access_token").getAsString();
                                String token = Store.readData(context);
                                Gson jon = new Gson();
                                JsonObject obj =  jon.fromJson(token, JsonObject.class);
                                String registration_token = obj.get("registration_id").getAsString();
                                String DeviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                                PostTokenToServer(access_token,registration_token,username,DeviceId);

                            }
                             String s = result;
                             Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
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
