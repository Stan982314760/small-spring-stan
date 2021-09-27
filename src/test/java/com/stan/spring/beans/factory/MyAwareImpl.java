package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;
import com.stan.spring.context.ApplicationContext;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description:
 */
public class MyAwareImpl implements BeanNameAware, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        if (context != null) {
            context = ctx;
        }
        System.out.println(ctx);
    }

    @Override
    public void setBeanName(String beanName) throws BeansException {
        System.out.println(beanName);
    }
}
