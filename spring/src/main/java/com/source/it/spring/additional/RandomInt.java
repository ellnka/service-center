package com.source.it.spring.additional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RandomInt {
    int min();
    int max();
}
