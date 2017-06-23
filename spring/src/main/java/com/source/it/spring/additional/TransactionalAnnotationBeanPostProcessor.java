package com.source.it.spring.additional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionalAnnotationBeanPostProcessor implements BeanPostProcessor {
    private List<String> list = new ArrayList<String>();

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Transactional.class)) {
            list.add(beanName);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        if (list.contains(beanName)) {
            final Class beanClass = bean.getClass();
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    new InvocationHandler() {
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            Object result;
                            synchronized (bean) {
                                System.out.println("**** Transaction started ******");
                                result = method.invoke(bean, args);
                                System.out.println("**** Transaction finished ******");
                            }
                            return result;
                        }
                    });
        }
        return bean;
    }
}
