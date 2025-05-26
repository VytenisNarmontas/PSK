package com.example.psk.service;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Stateless
public class AsyncWorker {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Future<String> doLongComputation() {
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "Computation finished at " + java.time.LocalTime.now();
            }
        };
        FutureTask<String> future = new FutureTask<>(task);
        executor.submit(future);
        return future;
    }
}
