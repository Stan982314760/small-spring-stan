package com.stan.aop;

import com.stan.spring.aop.advice.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: OrderServiceMethodBeforeAdvice
 */
public class OrderServiceMethodBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("OrderService before advice...");
    }
}
