package com.example.teqani.base.domain.repository;

import com.example.teqani.base.data.model.LoginBodyModel;
import com.example.teqani.base.data.model.LoginResponse;
import com.example.teqani.base.data.model.RegisterResponse;

import org.reactivestreams.Publisher;

import java.util.HashMap;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;

public interface UserRepository {

    Flowable<? extends RegisterResponse> register(HashMap<String,String> params);

    Completable verifyToken(String s);

    Flowable<LoginResponse> login(LoginBodyModel s);

    Completable generateOTP(String s);

    void saveUser(LoginResponse response);

    LoginResponse getUser();
}
