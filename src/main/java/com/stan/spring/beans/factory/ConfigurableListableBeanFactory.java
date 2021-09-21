package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: ConfigurableListableBeanFactory
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
