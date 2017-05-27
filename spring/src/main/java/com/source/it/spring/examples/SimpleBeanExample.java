package com.source.it.spring.examples;

import com.source.it.spring.beans.SimpleBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleBeanExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("simple-context.xml");
        System.out.println("Context refreshed");

        SimpleBean sb = context.getBean(SimpleBean.class);
        context.destroy();
        System.out.println(sb);
    }
}
