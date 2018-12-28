package com.example.teqani.base.domain.interactor.userUseCases;

import com.example.teqani.base.data.model.RegisterResponse;
import com.example.teqani.base.domain.exception.NoInternetConnectionException;
import com.example.teqani.base.domain.executer.PostExecutionThread;
import com.example.teqani.base.domain.executer.ThreadExecutor;
import com.example.teqani.base.domain.helper.FlowableUseCase;
import com.example.teqani.base.domain.interactor.InternetConnectionUseCase;
import com.example.teqani.base.domain.repository.UserRepository;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RegisterUseCase extends FlowableUseCase<RegisterResponse, HashMap<String, String>> {
    InternetConnectionUseCase internetConnectionUseCase;
    UserRepository userRepository;

    @Inject
    public RegisterUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, InternetConnectionUseCase internetConnectionUseCase, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.internetConnectionUseCase = internetConnectionUseCase;
        this.userRepository = userRepository;
    }

    @Override
    protected Flowable<RegisterResponse> buildUseCaseObservable(HashMap<String, String> params) {
        return internetConnectionUseCase.buildUseCaseObservable(null).switchMap(status -> {
            if (status) {
                return userRepository.register(params);
            } else {
                return Flowable.error(new NoInternetConnectionException());
            }
        });
    }
}
