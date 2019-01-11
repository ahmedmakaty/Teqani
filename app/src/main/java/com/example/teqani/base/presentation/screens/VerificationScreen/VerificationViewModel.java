package com.example.teqani.base.presentation.screens.VerificationScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.teqani.base.data.model.LoginBodyModel;
import com.example.teqani.base.domain.interactor.userUseCases.SignInUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.VerifyUseCase;

import io.reactivex.observers.DisposableCompletableObserver;

public class VerificationViewModel extends ViewModel {

    MutableLiveData<Boolean> emptyErrorSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> successSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> progressSLD = new MutableLiveData<>();

    VerifyUseCase verifyUseCase;
    SignInUseCase signInUseCase;

    String phoneNumber;

    public VerificationViewModel(VerifyUseCase verifyUseCase, SignInUseCase signInUseCase) {
        this.verifyUseCase = verifyUseCase;
        this.signInUseCase = signInUseCase;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void verify(String pin, String phone) {
        if (pin.matches("") || pin.length() < 4) {
            emptyErrorSLD.postValue(true);
        } else {
            progressSLD.postValue(true);

            LoginBodyModel params = new LoginBodyModel();
            params.setPin(pin);
            params.setPhone(phone);

            signInUseCase.execute(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    progressSLD.postValue(false);
                    successSLD.postValue(true);
                }

                @Override
                public void onError(Throwable e) {
                    progressSLD.postValue(false);
                }
            }, params);
        }
    }
}
