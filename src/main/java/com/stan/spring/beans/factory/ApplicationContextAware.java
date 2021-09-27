package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;
import com.stan.spring.context.ApplicationContext;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: ApplicationContextAware
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext ctx) throws BeansException;
}
