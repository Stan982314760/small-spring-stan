package com.stan.spring.aop;

import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: MethodMatcher
 */
public interface MethodMatcher {

    boolean match(Method method, Class<?> clazz);
}
