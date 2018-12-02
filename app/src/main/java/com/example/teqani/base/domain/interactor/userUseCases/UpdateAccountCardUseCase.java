package com.example.teqani.base.domain.interactor.userUseCases;

import com.example.teqani.base.domain.exception.NoInternetConnectionException;
import com.example.teqani.base.domain.executer.PostExecutionThread;
import com.example.teqani.base.domain.executer.ThreadExecutor;
import com.example.teqani.base.domain.helper.CompletableUseCase;
import com.example.teqani.base.domain.interactor.InternetConnectionUseCase;
import com.example.teqani.base.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class UpdateAccountCardUseCase extends CompletableUseCase<String> {

    UserRepository userRepository;
    InternetConnectionUseCase internetConnectionUseCase;

    @Inject
    public UpdateAccountCardUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository, InternetConnectionUseCase internetConnectionUseCase) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.internetConnectionUseCase = internetConnectionUseCase;
    }

    @Override
    protected Completable buildUseCaseObservable(String model) {
        return internetConnectionUseCase.buildUseCaseObservable(null).flatMapCompletable(status -> {
            if (status) {
                return Completable.complete();
            } else {
                return Completable.error(new NoInternetConnectionException());
            }
        });
    }
}
