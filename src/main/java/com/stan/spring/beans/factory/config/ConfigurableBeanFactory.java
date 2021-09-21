package com.stan.spring.beans.factory.config;

import com.stan.spring.beans.factory.HierarchicalBeanFactory;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: ConfigurableBeanFactory
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
