package com.source.it.spring.additional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("additional-context.xml");
        final Greater greater = context.getBean(Greater.class);
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Void>[] futures = new Future[5];

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            futures[i] = service.submit(new Callable<Void>() {
                public Void call() throws Exception {
                    greater.sayHi();
                    return null;
                }
            });
        }

        boolean isAllDone = false;
        while (!isAllDone) {
            isAllDone = true;
            for (Future<Void> future : futures) {
                if (!future.isDone()) {
                    isAllDone = false;
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Execution has taken " + (end - start) + "millis");
        service.shutdownNow();
    }
}
