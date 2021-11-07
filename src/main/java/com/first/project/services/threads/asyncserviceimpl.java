package com.first.project.services.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class asyncserviceimpl implements asyncservice {

    public Future<String> invoke() throws Throwable {

        System.out.println("asyn"+Thread.currentThread().getName());
        Thread.sleep(10000);
        return CompletableFuture.completedFuture("task completed ..");

    }

    @Override
    public void makereadythreadforsell(String jwtstring, float sale_percentage) throws InterruptedException {

    }
}
