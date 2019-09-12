package com.pronghorn.core.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;


@Component
public class SpringBeanHelper {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext = null;

    public <T> T getBean(Class<T> clazz, String beanName) {
        ApplicationContext context = getApplicationContext();
        return (T) context.getBean(beanName);
    }

    public <T> T getBean(Class<T> clazz) {
        ApplicationContext context = getApplicationContext();
        String beanName = ClassUtils.getShortName(clazz.getName());
        beanName = Introspector.decapitalize(beanName);
        return (T) context.getBean(beanName);
    }

    public static <T> T getBeans(Class<T> clazz) {
        ApplicationContext context = getApplicationContext();
        String beanName = ClassUtils.getShortName(clazz.getName());
        beanName = Introspector.decapitalize(beanName);
        return (T) context.getBean(beanName);
    }


    public static <T> T getBeans(Class<T> clazz, String beanName) {
        ApplicationContext context = getApplicationContext();
        return (T) context.getBean(beanName);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanHelper.applicationContext == null) {
            SpringBeanHelper.applicationContext = applicationContext;
        }
    }
}