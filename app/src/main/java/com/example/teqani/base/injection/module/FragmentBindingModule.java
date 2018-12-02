package com.example.teqani.base.injection.module;


import com.example.teqani.base.injection.module.screens.SplashScreenModule;
import com.example.teqani.base.injection.scopes.PerActivity;
import com.example.teqani.base.presentation.screens.SplashScreen.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashFragment bindSplashScreen();
}
