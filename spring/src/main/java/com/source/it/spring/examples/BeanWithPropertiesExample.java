package com.source.it.spring.examples;

import com.source.it.spring.beans.BeanWithProperties;
import com.source.it.spring.beans.ComplicatedBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanWithPropertiesExample {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("simple-context.xml");

        BeanWithProperties bean = context.getBean(BeanWithProperties.class);
        System.out.println(bean);
    }
}
