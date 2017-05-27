package com.source.it.spring.help.examples;

import com.source.it.spring.help.beans.SimpleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleContext {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("help/simple-context.xml");
        SimpleBean simpleBean =  (SimpleBean)context.getBean("simpleBean");

        System.out.println(simpleBean);
    }
}
