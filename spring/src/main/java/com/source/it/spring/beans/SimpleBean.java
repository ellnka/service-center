package com.source.it.spring.beans;

import javax.annotation.PostConstruct;

public class SimpleBean {
    private int x;
    private String str;
    private boolean b;

    public SimpleBean() {
        System.out.println("In constructor - Bean Created! " + this);
    }

    public SimpleBean(int x, String str, boolean b) {
        this.x = x;
        this.str = str;
        this.b = b;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    @PostConstruct
    public void init() {
        System.out.println("In init method. This = " + this);
    }

    public void destroy() {
        System.out.println("In destroy method");
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "x=" + x +
                ", str='" + str + '\'' +
                ", b=" + b +
                '}';
    }
}
