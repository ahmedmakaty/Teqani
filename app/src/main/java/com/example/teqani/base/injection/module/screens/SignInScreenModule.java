package com.example.teqani.base.injection.module.screens;

import com.example.teqani.base.domain.interactor.userUseCases.GenerateOTPUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.SignInUseCase;
import com.example.teqani.base.presentation.screens.SignInScreen.SignInViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInScreenModule {

    @Provides
    SignInViewModelFactory providesRegisterViewModelFactory(SignInUseCase signInUseCase, GenerateOTPUseCase generateOTPUseCase) {
        return new SignInViewModelFactory(signInUseCase, generateOTPUseCase);
    }
}
