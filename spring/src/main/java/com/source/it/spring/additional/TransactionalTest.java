package com.source.it.spring.additional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionalTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("additional-context.xml");
        Greater greater = context.getBean(Greater.class);

    }
}
