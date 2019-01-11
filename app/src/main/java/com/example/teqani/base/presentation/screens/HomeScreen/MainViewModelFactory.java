package com.example.teqani.base.presentation.screens.HomeScreen;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.teqani.base.domain.interactor.userUseCases.SimpleUserUseCase;

import javax.inject.Inject;

public class MainViewModelFactory implements ViewModelProvider.Factory{

    SimpleUserUseCase simpleUserUseCase;

    @Inject
    public MainViewModelFactory(SimpleUserUseCase simpleUserUseCase) {
        this.simpleUserUseCase = simpleUserUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(simpleUserUseCase);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
