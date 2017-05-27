package com.source.it.spring.help.beans;

public class SimpleBean {
    private int x;
    private String str;

    public SimpleBean() {
    }

    public SimpleBean(String str, int x) {
        this.str = str;
        this.x = x;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "x=" + x +
                ", str='" + str + '\'' +
                '}';
    }
}
