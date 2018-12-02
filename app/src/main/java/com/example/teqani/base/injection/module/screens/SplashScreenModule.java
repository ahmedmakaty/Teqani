package com.example.teqani.base.injection.module.screens;

import com.example.teqani.base.presentation.screens.SplashScreen.SplashViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashScreenModule {

    @Provides
    SplashViewModelFactory providesMainViewModelFactory() {
        return new SplashViewModelFactory();
    }
}
