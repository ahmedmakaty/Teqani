package com.example.teqani.base.domain.interactor.userUseCases;

import com.example.teqani.base.data.model.LoginBodyModel;
import com.example.teqani.base.data.model.LoginResponse;
import com.example.teqani.base.domain.exception.NoInternetConnectionException;
import com.example.teqani.base.domain.executer.PostExecutionThread;
import com.example.teqani.base.domain.executer.ThreadExecutor;
import com.example.teqani.base.domain.helper.CompletableUseCase;
import com.example.teqani.base.domain.helper.FlowableUseCase;
import com.example.teqani.base.domain.interactor.InternetConnectionUseCase;
import com.example.teqani.base.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class SignInUseCase extends FlowableUseCase<LoginResponse, LoginBodyModel> {

    InternetConnectionUseCase internetConnectionUseCase;
    UserRepository userRepository;

    @Inject
    public SignInUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, InternetConnectionUseCase internetConnectionUseCase, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.internetConnectionUseCase = internetConnectionUseCase;
        this.userRepository = userRepository;
    }


    @Override
    protected Flowable<LoginResponse> buildUseCaseObservable(LoginBodyModel s) {
        return internetConnectionUseCase.buildUseCaseObservable(null).switchMap(status -> {
            if (status) {
                return userRepository.login(s).switchMap(response -> {
                    userRepository.saveUser(response);
                    return Flowable.just(response);
                });
                //return userRepository.login(s);
            } else {
                return Flowable.error(new NoInternetConnectionException());
            }
        });
    }
}
