package com.example.teqani.base.presentation.screens.RegisterScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.teqani.base.data.model.RegisterResponse;
import com.example.teqani.base.domain.interactor.userUseCases.GenerateOTPUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.RegisterUseCase;

import java.util.HashMap;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.subscribers.DisposableSubscriber;

public class RegisterViewModel extends ViewModel {

    MutableLiveData<Boolean> progressSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> successSLD = new MutableLiveData<>();
    MutableLiveData<RegisterError> errorSLD = new MutableLiveData<>();
    RegisterError registerError = RegisterError.EMPTY_BOTH;

    RegisterUseCase registerUseCase;
    GenerateOTPUseCase generateOTPUseCase;

    public RegisterViewModel(RegisterUseCase registerUseCase, GenerateOTPUseCase generateOTPUseCase) {
        this.registerUseCase = registerUseCase;
        this.generateOTPUseCase = generateOTPUseCase;
    }

    public void register(String name, String phone) {
        progressSLD.postValue(true);
        if (name.matches("") && phone.matches("")) {
            progressSLD.postValue(false);
            registerError = RegisterError.EMPTY_BOTH;
        } else if (name.matches("")) {
            registerError = RegisterError.EMPTY_NAME;
            progressSLD.postValue(false);
        } else if (phone.matches("")) {
            registerError = RegisterError.EMPTY_PHONE;
            progressSLD.postValue(false);
        } else {
            registerError = RegisterError.CLEAR;

            HashMap<String, String> params = new HashMap<>();

            params.put("name", name);
            params.put("username", phone);

            registerUseCase.execute(new DisposableSubscriber<RegisterResponse>() {
                @Override
                public void onNext(RegisterResponse registerResponse) {

                    generateOTPUseCase.execute(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            successSLD.postValue(true);
                            progressSLD.postValue(false);
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressSLD.postValue(false);
                        }
                    }, phone);
                }

                @Override
                public void onError(Throwable t) {
                    progressSLD.postValue(false);
                }

                @Override
                public void onComplete() {
                    progressSLD.postValue(false);
                }
            }, params);
        }

        errorSLD.postValue(registerError);
    }


    enum RegisterError {
        EMPTY_NAME,
        EMPTY_PHONE,
        EMPTY_BOTH,
        CLEAR
    }
}
