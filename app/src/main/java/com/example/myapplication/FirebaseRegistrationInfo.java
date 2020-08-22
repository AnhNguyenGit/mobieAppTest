package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class FirebaseRegistrationInfo {
    @SerializedName("appId")
    private String appId;
    @SerializedName("registration_id")
    private String registration_id;
    @SerializedName("username")
    private  String username;
    @SerializedName("deviceInfo")
    private String deviceInfo;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
