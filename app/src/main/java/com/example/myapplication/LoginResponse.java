package com.example.myapplication;

import java.util.Date;

public class LoginResponse {
    private String access_token;
    private Date issued;
    private  Date expires;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
