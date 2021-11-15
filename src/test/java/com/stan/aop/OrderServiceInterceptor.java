package com.stan.aop;

import com.stan.spring.aop.MethodInterceptor;
import com.stan.spring.aop.MethodInvocation;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description:
 */
public class OrderServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("OrderServiceInterceptor before...");
        Object obj = invocation.proceed();
        System.out.println("OrderServiceInterceptor after...");
        return obj;
    }
}
