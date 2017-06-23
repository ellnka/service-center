package com.source.it.spring.littlebean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("little-bean-context.xml");
        context.getBean(LittleBean.class).sayHi();
    }
}
