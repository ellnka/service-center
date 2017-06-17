package com.source.it.spring.profiling;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {
    private List<String> list = new ArrayList<String>();
    private ProfilingController controller = new ProfilingController();

    public ProfilingAnnotationBeanPostProcessor() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(controller,
                new ObjectName("com.source.it.spring.profiling",
                        "controller",
                        "enabled"));
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
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
                            if (controller.isEnabled()) {
                                Object result;
                                System.out.println("**** Profiling  started ******");
                                long start = System.nanoTime();
                                result = method.invoke(bean, args);
                                long end = System.nanoTime();
                                System.out.println("**** Profilingfinished finished ******");
                                System.out.println("Method executed " + (end - start) + " nanos");
                                return result;
                            } else {
                                return method.invoke(bean, args);
                            }
                        }
                    });
        }
        return bean;
    }
}