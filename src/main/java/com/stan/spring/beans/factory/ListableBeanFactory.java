package com.stan.spring.beans.factory;

import java.util.Map;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: ListableBeanFactory
 */
public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> type);

    String[] getBeanDefinitionNames();
}
