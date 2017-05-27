package com.source.it.spring.help.beans;

import javax.annotation.PostConstruct;

public class InitMethodBean {

    static {
        System.out.println("In Static");
    }

    public InitMethodBean() {
        System.out.println("In constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("In init method");
    }
}
