package com.stan.spring.aop;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: Pointcut
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
