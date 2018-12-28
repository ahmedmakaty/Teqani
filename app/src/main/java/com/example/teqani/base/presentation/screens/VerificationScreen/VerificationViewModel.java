package com.example.teqani.base.presentation.screens.VerificationScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.teqani.base.domain.interactor.userUseCases.VerifyUseCase;

import io.reactivex.observers.DisposableCompletableObserver;

public class VerificationViewModel extends ViewModel {

    MutableLiveData<Boolean> emptyErrorSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> successSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> progressSLD = new MutableLiveData<>();

    VerifyUseCase verifyUseCase;

    public VerificationViewModel(VerifyUseCase verifyUseCase) {
        this.verifyUseCase = verifyUseCase;
    }

    public void verify(String s) {
        if (s.matches("") || s.length() < 4) {
            emptyErrorSLD.postValue(true);
        } else {
            progressSLD.postValue(true);
            verifyUseCase.execute(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    progressSLD.postValue(false);
                    successSLD.postValue(true);
                }

                @Override
                public void onError(Throwable e) {

                }
            }, s);
        }
    }
}
