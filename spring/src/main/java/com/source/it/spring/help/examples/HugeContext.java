package com.source.it.spring.help.examples;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HugeContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("help/huge-context.xml");

        System.out.println(context.getBean("complicatedBean"));
    }
}
