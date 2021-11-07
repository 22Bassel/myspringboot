package com.first.project.services.threads;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface asyncservice {
    @Async
    public void makereadythreadforsell(String jwtstring, float sale_percentage) throws InterruptedException;
}
