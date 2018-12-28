package com.example.teqani.base.data.remote.user;

import com.example.teqani.base.data.model.RegisterResponse;
import com.example.teqani.base.data.remote.ApiServiceInterface;
import com.example.teqani.base.data.remote.helper.HeadersHelper;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserRemoteImp implements UserRemote {

    private ApiServiceInterface apiServiceInterface;

    @Inject
    public UserRemoteImp(ApiServiceInterface apiServiceInterface) {
        this.apiServiceInterface = apiServiceInterface;
    }

    @Override
    public Flowable<? extends RegisterResponse> register(HashMap<String, String> params) {
        return apiServiceInterface.register(HeadersHelper.getHeaders(), params);
    }

    @Override
    public Completable verify(String s) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", s);
        return apiServiceInterface.verify(HeadersHelper.getHeaders(), params);
    }

    @Override
    public Completable login(String s) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", s);
        return apiServiceInterface.login(HeadersHelper.getHeaders(), params);
    }
}
