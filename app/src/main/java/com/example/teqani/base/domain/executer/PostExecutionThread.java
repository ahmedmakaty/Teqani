package com.example.teqani.base.domain.executer;

import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();

}
