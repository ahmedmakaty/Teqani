package com.example.teqani.base.injection.module.screens;

import com.example.teqani.base.domain.interactor.userUseCases.GenerateOTPUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.RegisterUseCase;
import com.example.teqani.base.presentation.screens.RegisterScreen.RegisterViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterScreenModule {

    @Provides
    RegisterViewModelFactory providesRegisterViewModelFactory(RegisterUseCase registerUseCase, GenerateOTPUseCase generateOTPUseCase) {
        return new RegisterViewModelFactory(registerUseCase, generateOTPUseCase);
    }
}
