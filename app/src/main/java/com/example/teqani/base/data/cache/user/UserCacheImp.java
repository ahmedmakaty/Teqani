package com.example.teqani.base.data.cache.user;

import com.example.teqani.base.data.Constants;
import com.example.teqani.base.data.cache.helper.ComplexPreferences;
import com.example.teqani.base.data.model.LoginResponse;

import javax.inject.Inject;

public class UserCacheImp implements UserCache {

    ComplexPreferences complexPreferences;

    @Inject
    public UserCacheImp(ComplexPreferences complexPreferences) {
        this.complexPreferences = complexPreferences;
    }

    @Override
    public void saveUser(LoginResponse user) {
        complexPreferences.putObject(Constants.USER, user);
        complexPreferences.commmit();
    }

    @Override
    public LoginResponse getUser() {
        return complexPreferences.getObject(Constants.USER, LoginResponse.class);
    }
}
