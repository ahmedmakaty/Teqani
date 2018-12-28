package com.example.teqani.base.injection.module;


import com.example.teqani.base.injection.module.screens.RegisterScreenModule;
import com.example.teqani.base.injection.module.screens.SignInScreenModule;
import com.example.teqani.base.injection.module.screens.SplashScreenModule;
import com.example.teqani.base.injection.module.screens.VerificationScreenModule;
import com.example.teqani.base.injection.scopes.PerActivity;
import com.example.teqani.base.presentation.screens.RegisterScreen.RegisterFragment;
import com.example.teqani.base.presentation.screens.SignInScreen.SignInFragment;
import com.example.teqani.base.presentation.screens.SplashScreen.SplashFragment;
import com.example.teqani.base.presentation.screens.VerificationScreen.VerificationFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {SplashScreenModule.class})
    abstract SplashFragment bindSplashScreen();

    @PerActivity
    @ContributesAndroidInjector(modules = {RegisterScreenModule.class})
    abstract RegisterFragment bindRegisterScreen();

    @PerActivity
    @ContributesAndroidInjector(modules = {VerificationScreenModule.class})
    abstract VerificationFragment bindVerificationScreen();

    @PerActivity
    @ContributesAndroidInjector(modules = {SignInScreenModule.class})
    abstract SignInFragment bindSignInScreen();
}
