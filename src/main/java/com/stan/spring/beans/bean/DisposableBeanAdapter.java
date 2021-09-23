package com.stan.spring.beans.bean;

import cn.hutool.core.util.StrUtil;
import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/09/23
 * @Description: DisposableBeanAdapter
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private final String  destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if (StrUtil.isNotEmpty(destroyMethodName) &&
                !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method destroyMethod = bean.getClass().getDeclaredMethod(destroyMethodName);
            if (destroyMethod == null)
                throw new BeansException("can not found destroy method [" + destroyMethodName + "] in bean [" + beanName + "]");

            destroyMethod.invoke(bean);
        }
    }
}
