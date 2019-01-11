package com.example.teqani.base.injection.module.screens;

import com.example.teqani.base.domain.interactor.userUseCases.SimpleUserUseCase;
import com.example.teqani.base.presentation.screens.HomeScreen.MainViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModule {

    @Provides
    MainViewModelFactory providesMainViewModelFactory(SimpleUserUseCase simpleUserUseCase) {
        return new MainViewModelFactory(simpleUserUseCase);
    }
}