package com.source.it.spring.littlebean;

public class SuperLittleBean extends MyLittleBean implements LittleBean {
    private String message = "Hello, I'm a SUPER bean";

    public void sayHi() {
        System.out.println(message);
    }

}
