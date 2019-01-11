package com.example.teqani.base.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("plainUser")
    @Expose
    private PlainUser plainUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PlainUser getPlainUser() {
        return plainUser;
    }

    public void setPlainUser(PlainUser plainUser) {
        this.plainUser = plainUser;
    }

}