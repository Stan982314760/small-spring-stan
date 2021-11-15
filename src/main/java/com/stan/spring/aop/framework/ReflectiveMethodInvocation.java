package com.stan.spring.aop.framework;

import com.stan.spring.aop.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: ReflectiveMethodInvocation
 */
public class ReflectiveMethodInvocation implements MethodInvocation  {

    private final Object target;

    private final Method method;

    private final Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }


    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }


    public Object[] getArguments() {
        return args;
    }

    public Method getMethod() {
        return method;
    }


}
