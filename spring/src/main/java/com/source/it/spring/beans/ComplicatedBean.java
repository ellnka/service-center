package com.source.it.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class ComplicatedBean {
    @Autowired
    private SimpleBean simpleBean;
    private String qualifier;

    public void setSimpleBean(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    @Override
    public String toString() {
        return "ComplicatedBean{" +
                "simpleBean=" + simpleBean +
                ", qualifier='" + qualifier + '\'' +
                '}';
    }
}
