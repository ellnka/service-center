package com.source.it.spring.additional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

public class RandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            RandomInt annotation = field.getAnnotation(RandomInt.class);
            if (annotation !=null) {
                field.setAccessible(true);
                Random random = new Random();
                int min = annotation.min();
                int max = annotation.max();
                int value = min + random.nextInt(max - min);
                ReflectionUtils.setField(field, bean, value);
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
