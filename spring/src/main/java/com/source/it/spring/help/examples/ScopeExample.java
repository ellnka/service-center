package com.source.it.spring.help.examples;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("help/huge-context.xml");
        context.getBean("initMethodBean");
        context.getBean("initMethodBean");
    }
}
