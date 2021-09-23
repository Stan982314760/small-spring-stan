package com.stan.spring.beans.factory;

import com.stan.spring.beans.factory.config.BeanPostProcessor;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: MyBeanPostProcessor
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessor: before initialization, " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("MyBeanPostProcessor: after initialization, " + beanName);
        return bean;
    }
}
