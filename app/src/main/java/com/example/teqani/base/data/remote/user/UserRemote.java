package com.example.teqani.base.data.remote.user;

import io.reactivex.Flowable;

public interface UserRemote {
    Flowable<String> generateToken(String deviceId);

}
