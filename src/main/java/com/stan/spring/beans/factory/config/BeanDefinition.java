package com.stan.spring.beans.factory.config;

import com.stan.spring.beans.PropertyValues;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: BeanDefinition
 */
public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;


    public BeanDefinition(Class<?> beanClass) {
        this(beanClass, null);
    }


    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValue) {
        this.propertyValues = propertyValue;
    }
}
