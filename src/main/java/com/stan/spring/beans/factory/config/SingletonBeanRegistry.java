package com.stan.spring.beans.factory.config;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: SingletonBeanRegistry
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object bean);
}
