package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: InstantiationStrategy
 */
public interface InstantiationStrategy {

    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor<?> constructor, Object[] args);
}
