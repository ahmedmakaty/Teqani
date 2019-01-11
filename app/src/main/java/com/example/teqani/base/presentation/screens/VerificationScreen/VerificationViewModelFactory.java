package com.example.teqani.base.presentation.screens.VerificationScreen;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.teqani.base.domain.interactor.userUseCases.SignInUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.VerifyUseCase;

import javax.inject.Inject;

public class VerificationViewModelFactory implements ViewModelProvider.Factory {

    VerifyUseCase verifyUseCase;
    SignInUseCase signInUseCase;

    @Inject
    public VerificationViewModelFactory(VerifyUseCase verifyUseCase, SignInUseCase signInUseCase) {
        this.verifyUseCase = verifyUseCase;
        this.signInUseCase = signInUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(VerificationViewModel.class)) {
            return (T) new VerificationViewModel(verifyUseCase, signInUseCase);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
