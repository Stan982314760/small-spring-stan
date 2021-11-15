package com.stan.spring.aop;

import com.stan.spring.aop.advice.Advice;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: MethodInterceptor
 */
public interface MethodInterceptor extends Advice {

    Object invoke(MethodInvocation methodInvocation) throws Throwable;

}
