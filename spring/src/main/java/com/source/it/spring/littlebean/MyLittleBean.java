package com.source.it.spring.littlebean;

@DeprecatedWithNewImpl(newImpl = SuperLittleBean.class)
public class MyLittleBean implements LittleBean {
    private String message = "Hello, I'm a little bean";

    public void sayHi() {
        System.out.println(message);
    }
}
