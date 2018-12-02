package com.example.teqani.base.presentation;

import com.example.teqani.base.domain.executer.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UiThread implements PostExecutionThread {

    @Inject
    public UiThread() {
    }

    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}


