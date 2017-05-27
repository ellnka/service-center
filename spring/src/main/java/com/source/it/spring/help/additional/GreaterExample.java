package com.source.it.spring.help.additional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreaterExample {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("help/greater-context.xml");
        while (true) {
            context.getBean(Greater.class).sayHi();
            Thread.sleep(1000);
        }
    }
}
