package com.stan.spring.beans.factory;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: BeanClassLoaderAware
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
