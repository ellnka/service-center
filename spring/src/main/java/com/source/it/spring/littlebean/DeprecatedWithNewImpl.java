package com.source.it.spring.littlebean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedWithNewImpl {
    Class newImpl();
}
