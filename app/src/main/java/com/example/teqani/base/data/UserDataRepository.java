package com.example.teqani.base.data;


import com.example.teqani.base.data.cache.user.UserCache;
import com.example.teqani.base.data.remote.user.UserRemote;
import com.example.teqani.base.domain.repository.UserRepository;

import javax.inject.Inject;

public class UserDataRepository implements UserRepository {

    private UserCache userCache;
    private UserRemote userRemote;

    @Inject
    public UserDataRepository(UserCache userCache, UserRemote userRemote) {
        this.userCache = userCache;
        this.userRemote = userRemote;
    }

}
