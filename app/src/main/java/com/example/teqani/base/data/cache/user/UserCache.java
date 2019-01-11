package com.example.teqani.base.data.cache.user;

import com.example.teqani.base.data.model.LoginResponse;

public interface UserCache {
    void saveUser(LoginResponse user);

    LoginResponse getUser();
}
