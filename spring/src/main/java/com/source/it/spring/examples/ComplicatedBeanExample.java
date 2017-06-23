package com.source.it.spring.examples;

import com.source.it.spring.beans.ComplicatedBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComplicatedBeanExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("simple-context.xml");

        ComplicatedBean cb = context.getBean(ComplicatedBean.class);
        System.out.println(cb);
    }
}
