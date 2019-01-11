package com.example.teqani.base.data;


import com.example.teqani.base.data.cache.user.UserCache;
import com.example.teqani.base.data.model.LoginBodyModel;
import com.example.teqani.base.data.model.RegisterResponse;
import com.example.teqani.base.data.remote.user.UserRemote;
import com.example.teqani.base.domain.repository.UserRepository;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserDataRepository implements UserRepository {

    private UserCache userCache;
    private UserRemote userRemote;

    @Inject
    public UserDataRepository(UserCache userCache, UserRemote userRemote) {
        this.userCache = userCache;
        this.userRemote = userRemote;
    }

    @Override
    public Flowable<? extends RegisterResponse> register(HashMap<String, String> params) {
        return userRemote.register(params);
    }

    @Override
    public Completable verifyToken(String s) {
        return userRemote.verify(s);
    }

    @Override
    public Completable login(LoginBodyModel s) {
        return userRemote.login(s);
    }

    @Override
    public Completable generateOTP(String s) {
        return userRemote.generateOTP(s);
    }
}
