package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: BeanNameAware
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName) throws BeansException;
}
