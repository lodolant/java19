package com.github.lodolant.java19.features;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThread {
    private static ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    public static <RESULT> Future<RESULT> submit(Callable<RESULT> callable) {
        return executorService.submit(callable);
    }
}
