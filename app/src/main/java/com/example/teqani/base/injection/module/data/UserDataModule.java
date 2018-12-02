package com.example.teqani.base.injection.module.data;

import com.example.teqani.base.data.UserDataRepository;
import com.example.teqani.base.data.cache.user.UserCache;
import com.example.teqani.base.data.cache.user.UserCacheImp;
import com.example.teqani.base.data.remote.ApiServiceInterface;
import com.example.teqani.base.data.remote.user.UserRemote;
import com.example.teqani.base.data.remote.user.UserRemoteImp;
import com.example.teqani.base.domain.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class UserDataModule {

    @Provides
    UserRepository providesUserDataRepository(UserCache userCache, UserRemote userRemote) {
        return new UserDataRepository(userCache, userRemote);
    }

    @Provides
    UserCache providesUserCache() {
        return new UserCacheImp();
    }

    @Provides
    UserRemote providesUserRemote(ApiServiceInterface apiServiceInterface) {
        return new UserRemoteImp(apiServiceInterface);
    }
}
