package com.example.teqani.base.presentation.screens.SignInScreen;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.teqani.base.App;
import com.example.teqani.base.data.model.ErrorResponse;
import com.example.teqani.base.domain.exception.NoInternetConnectionException;
import com.example.teqani.base.domain.interactor.userUseCases.GenerateOTPUseCase;
import com.example.teqani.base.domain.interactor.userUseCases.SignInUseCase;
import com.example.teqani.base.helper.LocaleHelper;
import com.google.gson.Gson;

import io.reactivex.observers.DisposableCompletableObserver;
import retrofit2.HttpException;

public class SignInViewModel extends ViewModel {

    MutableLiveData<Boolean> progressSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> successSLD = new MutableLiveData<>();
    MutableLiveData<String> apiErrorSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> noInternetConnectionSLD = new MutableLiveData<>();
    MutableLiveData<Boolean> emptyErrorSLD = new MutableLiveData<>();

    SignInUseCase signInUseCase;
    GenerateOTPUseCase generateOTPUseCase;

    public SignInViewModel(SignInUseCase signInUseCase, GenerateOTPUseCase generateOTPUseCase) {
        this.signInUseCase = signInUseCase;
        this.generateOTPUseCase = generateOTPUseCase;
    }

    public void login(String s) {
        if (s.matches("")) {
            emptyErrorSLD.postValue(true);
        } else {
            progressSLD.postValue(true);

            generateOTPUseCase.execute(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    successSLD.postValue(true);
                }

                @Override
                public void onError(Throwable t) {
                    progressSLD.postValue(false);
                    ErrorResponse error = null;
                    try {
                        error = new Gson().fromJson(((HttpException) t).response().errorBody().string(), ErrorResponse.class);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (t instanceof HttpException) {
                        apiErrorSLD.postValue((LocaleHelper.getInstance(App.getInstance().getApplicationContext()).getLanguage().matches("en") ? error.getMessageEn() : error.getMessage()));
                    } else if (t instanceof NoInternetConnectionException) {
                        noInternetConnectionSLD.postValue(true);
                    }
                }
            }, s);

        }
    }
}
