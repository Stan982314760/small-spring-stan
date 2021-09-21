package com.stan.spring.beans.factory.config;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: BeanPostProcessor
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}
