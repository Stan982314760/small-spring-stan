package com.stan.spring.beans.factory.support;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.bean.DisposableBean;
import com.stan.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: DefaultSingletonBeanRegistry
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NUL_OBJ = new Object();
    private Map<String, Object> singletonObjects = new HashMap<>();
    private Map<String, DisposableBean> disposableBeans = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singleton) {
        singletonObjects.put(beanName, singleton);
    }


    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }


    public void destroySingletons() {
        String[] names = disposableBeans.keySet().toArray(new String[0]);
        for (int i = names.length - 1; i >= 0; i--) {
            DisposableBean disposableBean = disposableBeans.remove(names[i]);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("invoke bean [" + names[i] + "] destroy method error", e);
            }
        }
    }
}
