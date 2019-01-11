package com.example.teqani.base.presentation.screens.SignInScreen;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.teqani.base.domain.interactor.userUseCases.GenerateOTPUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.SignInUseCase;
import com.example.teqani.base.presentation.screens.RegisterScreen.RegisterViewModel;

import javax.inject.Inject;

public class SignInViewModelFactory implements ViewModelProvider.Factory {

    SignInUseCase signInUseCase;
    GenerateOTPUseCase generateOTPUseCase;

    @Inject
    public SignInViewModelFactory(SignInUseCase signInUseCase, GenerateOTPUseCase generateOTPUseCase) {
        this.signInUseCase = signInUseCase;
        this.generateOTPUseCase = generateOTPUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignInViewModel.class)) {
            return (T) new SignInViewModel(signInUseCase, generateOTPUseCase);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
