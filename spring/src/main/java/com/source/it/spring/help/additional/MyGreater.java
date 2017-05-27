package com.source.it.spring.help.additional;

public class MyGreater implements Greater{
    private String message;

    @RandomInt(min = 1, max = 5)
    private int times;

    public void setMessage(String message) {
        this.message = message;
    }

    public void sayHi() {
        System.out.println(message);
    }
}
