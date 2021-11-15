package com.stan.spring.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.stan.spring.beans.factory.BeanFactoryAware;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: InstantiationAwareBeanPostProcessor
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor, BeanFactoryAware {


    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;


    //Object postProcessAfterInstantiation(Class<?> beanClass, String beanName) throws BeansException;
    //
    //
    //PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;
    //
    //
    //default Object getEarlyBeanReference(Object bean, String beanName) {
    //    return bean;
    //}

}
