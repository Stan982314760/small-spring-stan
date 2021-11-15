package com.stan.spring.aop;

import com.stan.spring.util.ClassUtils;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: TargetSource
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }


    public Class<?>[] getTargetClass() {
        Class<?> targetClass = target.getClass();
        targetClass = ClassUtils.isCglibObject(targetClass) ? targetClass.getSuperclass() : targetClass;
        return targetClass.getInterfaces();
    }
}
