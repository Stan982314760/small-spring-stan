package com.stan.spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: MethodBeforeAdvice
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, Object target) throws Throwable;

}
