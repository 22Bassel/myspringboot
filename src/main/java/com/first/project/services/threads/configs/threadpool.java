package com.first.project.services.threads.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class threadpool extends AsyncConfigurerSupport {

    public threadpool() {
        super();
    }

    @Override
    public Executor getAsyncExecutor(){

        ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
      //  executor.setMaxPoolSize(7);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("");
        executor.setRejectedExecutionHandler(
                (r,executor1)->{
                    try {
                        executor1.getQueue().put(r);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                });
        executor.initialize();
        return executor;
    }





}
