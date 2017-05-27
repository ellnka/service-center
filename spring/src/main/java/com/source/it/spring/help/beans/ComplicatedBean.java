package com.source.it.spring.help.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class ComplicatedBean {

    @Autowired
    private SimpleBean simpleBean;

    private String str;

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "ComplicatedBean{" +
                "simpleBean=" + simpleBean +
                ", str='" + str + '\'' +
                '}';
    }
}
