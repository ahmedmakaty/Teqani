package com.example.teqani.base.injection.module;

import android.app.Application;
import android.content.Context;

import com.example.teqani.base.data.Executor.JobExecutor;
import com.example.teqani.base.data.remote.ApiServiceInterface;
import com.example.teqani.base.domain.executer.PostExecutionThread;
import com.example.teqani.base.domain.executer.ThreadExecutor;
import com.example.teqani.base.presentation.UiThread;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {

    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    ApiServiceInterface provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiServiceInterface.class);
    }

    @Provides
    JobExecutor providesJobExecutor() {
        return JobExecutor.getInstance();
    }

    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }
}
