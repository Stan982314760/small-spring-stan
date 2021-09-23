package com.stan.spring.beans.factory;

import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.stan.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: MyBeanFactoryPostProcessor
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        if (beanFactory instanceof DefaultListableBeanFactory) {
            DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
            listableBeanFactory.registerBeanDefinition("myBeanPostProcessor", new BeanDefinition(MyBeanPostProcessor.class));
            System.out.println("MyBeanFactoryPostProcessor: register myBeanPostProcessor...");
        } else {
            System.out.println("MyBeanFactoryPostProcessor: beanFactory post processor, do nothing...");
        }
    }
}
