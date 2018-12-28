package com.example.teqani.base.domain.interactor.userUseCases;

import com.example.teqani.base.domain.exception.NoInternetConnectionException;
import com.example.teqani.base.domain.executer.PostExecutionThread;
import com.example.teqani.base.domain.executer.ThreadExecutor;
import com.example.teqani.base.domain.helper.CompletableUseCase;
import com.example.teqani.base.domain.interactor.InternetConnectionUseCase;
import com.example.teqani.base.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class SignInUseCase extends CompletableUseCase<String> {

    InternetConnectionUseCase internetConnectionUseCase;
    UserRepository userRepository;

    @Inject
    public SignInUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, InternetConnectionUseCase internetConnectionUseCase, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.internetConnectionUseCase = internetConnectionUseCase;
        this.userRepository = userRepository;
    }


    @Override
    protected Completable buildUseCaseObservable(String s) {
        return internetConnectionUseCase.buildUseCaseObservable(null).flatMapCompletable(status -> {
            if (status) {
                return userRepository.login(s);
            } else {
                return Completable.error(new NoInternetConnectionException());
            }
        });
    }
}
