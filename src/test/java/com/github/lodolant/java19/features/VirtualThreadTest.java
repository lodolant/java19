package com.github.lodolant.java19.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class VirtualThreadTest {
    @Test
    public void test() throws ExecutionException, InterruptedException {
        // GIVEN
        Callable<String> task = () -> "Result";

        // WHEN
        Future<String> submit = VirtualThread.submit(task);

        // THEN
        String result = submit.get();
        Assertions.assertEquals("Result", result);
    }
}
