package com.example.teqani.base.presentation.screens.HomeScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.teqani.base.data.model.LoginResponse;
import com.example.teqani.base.domain.interactor.userUseCases.SimpleUserUseCase;

public class MainViewModel extends ViewModel {

    MutableLiveData<Boolean> userReadySLD = new MutableLiveData<>();

    SimpleUserUseCase simpleUserUseCase;

    LoginResponse loginResponse;

    public MainViewModel(SimpleUserUseCase simpleUserUseCase) {
        this.simpleUserUseCase = simpleUserUseCase;
    }

    public void getUser() {
        loginResponse = simpleUserUseCase.getUser();
        if (loginResponse != null)
            userReadySLD.postValue(true);
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }
}
