package com.example.teqani.base.injection;

import android.app.Application;

import com.example.teqani.base.App;
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
        AndroidSupportInjectionModule.class,
        FragmentBindingModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
