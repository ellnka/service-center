package com.source.it.spring.help.examples;

import com.source.it.spring.help.beans.SimpleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComplicatedContext {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("help/complicated-context.xml");
        SimpleBean simpleBean =  (SimpleBean)context.getBean("simpleBean");

        System.out.println(simpleBean);

        System.out.println(context.getBean("anotherSimpleBean"));
    }
}
