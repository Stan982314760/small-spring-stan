package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.factory.config.BeanDefinition;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: BeanDefinitionRegistry
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
