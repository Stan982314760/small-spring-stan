package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: SimpleInstantiationStrategy
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {


    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor<?> constructor, Object[] args) {
        Class<?> clazz = beanDefinition.getBeanClass();
        Object bean;
        try {
            if (constructor != null) {
                bean = clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                bean = clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException(e);
        }

        return bean;
    }
}
