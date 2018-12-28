package com.example.teqani.base.injection.module.screens;

import com.example.teqani.base.domain.interactor.userUseCases.VerifyUseCase;
import com.example.teqani.base.presentation.screens.VerificationScreen.VerificationViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class VerificationScreenModule {

    @Provides
    VerificationViewModelFactory providesVerificationViewModelFactory(VerifyUseCase verifyUseCase) {
        return new VerificationViewModelFactory(verifyUseCase);
    }
}
