package com.source.it.spring.additional;

import javax.annotation.PostConstruct;

@Transactional
public class MyGreater implements Greater {
    private String message;

    @RandomInt(min = 1, max = 1)
    private int random;

    public void setMessage(String message) {
        this.message = message;
    }

    @PostContext
    public void sayHi() {
        for (int i = 0; i < random; i++) {
            System.out.println(message);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}
