package com.example.teqani.base.presentation.screens.RegisterScreen;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.teqani.base.domain.interactor.userUseCases.GenerateOTPUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.RegisterUseCase;

import javax.inject.Inject;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {

    RegisterUseCase registerUseCase;
    GenerateOTPUseCase generateOTPUseCase;

    @Inject
    public RegisterViewModelFactory(RegisterUseCase registerUseCase, GenerateOTPUseCase generateOTPUseCase) {
        this.registerUseCase = registerUseCase;
        this.generateOTPUseCase = generateOTPUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(registerUseCase, generateOTPUseCase);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
