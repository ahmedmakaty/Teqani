package com.example.teqani.base;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.support.v4.app.Fragment;

import com.example.teqani.base.helper.LocaleHelper;
import com.example.teqani.base.injection.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class App extends Application implements HasActivityInjector, HasSupportFragmentInjector, HasServiceInjector {

    private static App sInstance = null;
    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    public DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    public static App getInstance() {
        return sInstance;
    }

    public static void switchLanguage(Activity activity, String country, String language) {
        LocaleHelper.getInstance(activity).setLocale(getInstance().getApplicationContext(),
                country, language);
//        activity.startActivity(new Intent(activity, SplashActivity.class));
//        activity.finishAffinity();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }
}
