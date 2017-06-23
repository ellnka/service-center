package com.source.it.spring.profiling;

@Profiling
public class MyWorker implements Worker {
    public void doWork() {
        System.out.println("Working.....");
    }
}
