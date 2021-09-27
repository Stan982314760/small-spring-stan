package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: BeanFactoryAware
 */
public interface BeanFactoryAware extends Aware {


    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
