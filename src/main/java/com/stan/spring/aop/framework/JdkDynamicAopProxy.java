package com.stan.spring.aop.framework;

import cn.hutool.core.util.ClassUtil;
import com.stan.spring.aop.AdvisedSupport;
import com.stan.spring.aop.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: JdkDynamicAopProxy
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(ClassUtil.getClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (supportMethod(method)) {
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }

        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }


    private boolean supportMethod(Method method) {
        return advisedSupport.getMethodMatcher().match(method, advisedSupport.getTargetSource().getTarget().getClass());
    }
}
