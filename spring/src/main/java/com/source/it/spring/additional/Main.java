package com.source.it.spring.additional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("additional-context.xml");
        MyGreater greater = context.getBean(MyGreater.class);
        greater.sayHi();
    }
}
