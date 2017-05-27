package com.source.it.spring.beans;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class BeanWithProperties {
    @Value("${x}")
    private int x;

    @Value("${str}")
    private String str;

    @PostConstruct
    public void init() {
        System.out.println(this);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "BeanWithProperties{" +
                "x=" + x +
                ", str='" + str + '\'' +
                '}';
    }
}
