package com.example.teqani.base.injection.module;

import com.example.teqani.base.injection.module.screens.MainScreenModule;
import com.example.teqani.base.injection.scopes.PerActivity;
import com.example.teqani.base.presentation.screens.HomeScreen.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainScreenModule.class})
    abstract MainActivity bindMainScreen();
}
