package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: CglibInstantiationStrategy
 */
public class CglibInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor<?> constructor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(NoOp.INSTANCE);
        if (constructor == null)
            return enhancer.create();

        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
