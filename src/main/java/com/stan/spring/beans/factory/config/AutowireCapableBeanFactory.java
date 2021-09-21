package com.stan.spring.beans.factory.config;

import com.stan.spring.beans.factory.BeanFactory;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: AutowireCapableBeanFactory
 */
public interface AutowireCapableBeanFactory extends BeanFactory {


    Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName);


    Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName);
}
