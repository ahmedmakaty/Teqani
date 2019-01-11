package com.example.teqani.base.injection;

import android.app.Application;

import com.example.teqani.base.App;
import com.example.teqani.base.injection.module.ActivityBindingModule;
import com.example.teqani.base.injection.module.AppModule;
import com.example.teqani.base.injection.module.FragmentBindingModule;
import com.example.teqani.base.injection.module.NetworkModule;
import com.example.teqani.base.injection.module.data.UserDataModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {NetworkModule.class,
        UserDataModule.class,
        AppModule.class,
        AndroidSupportInjectionModule.class,
        FragmentBindingModule.class,
        ActivityBindingModule.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
