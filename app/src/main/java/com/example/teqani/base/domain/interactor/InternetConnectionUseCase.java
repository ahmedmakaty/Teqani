package com.example.teqani.base.domain.interactor;

import com.example.teqani.base.domain.executer.PostExecutionThread;
import com.example.teqani.base.domain.executer.ThreadExecutor;
import com.example.teqani.base.domain.helper.FlowableUseCase;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class InternetConnectionUseCase extends FlowableUseCase<Boolean, Void> {

    @Inject
    public InternetConnectionUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    public Flowable<Boolean> buildUseCaseObservable(Void v) {
        return ReactiveNetwork
                .checkInternetConnectivity()
                .toFlowable();
    }
}

