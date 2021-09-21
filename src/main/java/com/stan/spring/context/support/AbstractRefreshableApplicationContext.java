package com.stan.spring.context.support;

import com.stan.spring.beans.factory.ConfigurableListableBeanFactory;
import com.stan.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: AbstractRefreshableApplicationContext
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
    }


    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }


    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
