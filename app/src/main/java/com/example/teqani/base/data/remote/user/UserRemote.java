package com.example.teqani.base.data.remote.user;

import com.example.teqani.base.data.model.LoginBodyModel;
import com.example.teqani.base.data.model.LoginResponse;
import com.example.teqani.base.data.model.RegisterResponse;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface UserRemote {

    Flowable<? extends RegisterResponse> register(HashMap<String,String> params);

    Completable verify(String s);

    Flowable<LoginResponse> login(LoginBodyModel s);

    Completable generateOTP(String s);
}
