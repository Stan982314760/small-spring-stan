package com.stan.spring.beans.factory.config;

import com.stan.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: BeanFactoryPostProcessor
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
