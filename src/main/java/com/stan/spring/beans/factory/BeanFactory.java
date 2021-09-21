package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: BeanFactory
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object[] args) throws BeansException;

    <T> T getBean(String beanName, Class<T> type) throws BeansException;
}
