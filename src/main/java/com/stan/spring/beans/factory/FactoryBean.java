package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: FactoryBean
 */
public interface FactoryBean<T> {

    boolean isSingleton();

    Class<T> getObjectType();

    T getObject() throws BeansException;
}
