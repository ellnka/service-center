package com.source.it.spring.help.additional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class RandomIntAnnotationBeanPostProcessor implements BeanPostProcessor{
    private MyGreaterProfilingController controller = new MyGreaterProfilingController();

    public RandomIntAnnotationBeanPostProcessor() throws Exception{
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        beanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
    }

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            RandomInt r = field.getAnnotation(RandomInt.class);
            if (r != null) {
                field.setAccessible(true);
                int min = r.min();
                int max = r.max();
                Random random = new Random();
                int value = min + random.nextInt(max - min);
                ReflectionUtils.setField(field, o, value);
            }
        }
        return o;
    }

    public Object postProcessAfterInitialization(final Object o, String s) throws BeansException {
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),
                o.getClass().getInterfaces(),
                new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (controller.isEnabled()) {
                    long start = System.nanoTime();
                    Object result = method.invoke(o, args);
                    long end = System.nanoTime();
                    System.out.println("Taken " + (end - start) + "nanos");
                    return result;
                } else {
                    System.out.println("Disabled!");
                    return method.invoke(o, args);
                }
            }
        });
    }
}
