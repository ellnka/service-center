package com.source.it.spring.profiling;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("profiling-context.xml");
        Worker bean = context.getBean(Worker.class);

        while (true) {
            bean.doWork();
            Thread.sleep(1000);
        }
    }
}
