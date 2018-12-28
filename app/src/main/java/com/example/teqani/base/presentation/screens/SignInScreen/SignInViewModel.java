package com.example.teqani.base.presentation.screens.SignInScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.teqani.base.domain.interactor.userUseCases.SignInUseCase;

import io.reactivex.observers.DisposableCompletableObserver;

public class SignInViewModel extends ViewModel {

    MutableLiveData<Boolean> progressSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> successSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> emptyErrorSLD = new MutableLiveData<>();

    SignInUseCase signInUseCase;

    public SignInViewModel(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    public void login(String s) {
        if (s.matches("")) {
            emptyErrorSLD.postValue(true);
        } else {
            progressSLD.postValue(true);
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
            }, s);
        }
    }
}
