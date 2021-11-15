package com.stan.spring.aop.framework.adapter;

import com.stan.spring.aop.MethodInterceptor;
import com.stan.spring.aop.MethodInvocation;
import com.stan.spring.aop.advice.MethodBeforeAdvice;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: MethodBeforeAdviceInterceptor
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice beforeAdvice;

    public MethodBeforeAdviceInterceptor() {
    }


    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        beforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }


    public void setBeforeAdvice(MethodBeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

}
