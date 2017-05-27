package com.source.it.spring.additional;

public class MyGreater {
    private String message;

    @RandomInt(min = 2, max = 5)
    private int random;

    public void setMessage(String message) {
        this.message = message;
    }

    public void sayHi() {
        for (int i = 0; i < random; i++) {
            System.out.println(message);
        }

    }
}
