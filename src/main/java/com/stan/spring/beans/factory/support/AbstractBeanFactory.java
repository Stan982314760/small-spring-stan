package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanPostProcessor;
import com.stan.spring.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: AbstractBeanFactory
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> postProcessors = new ArrayList<>();


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        postProcessors.remove(beanPostProcessor);
        postProcessors.add(beanPostProcessor);
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }


    @Override
    public Object getBean(String beanName, Object[] args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String beanName, Class<T> type) throws BeansException {
        return (T) getBean(beanName);
    }

    private Object doGetBean(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null)
            return bean;

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }


    protected List<BeanPostProcessor> getBeanPostProcessors() {
        return postProcessors;
    }


    protected abstract Object createBean(String beanName, BeanDefinition bd, Object[] args);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

}
