package com.stan.spring.aop;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: ClassFilter
 */
public interface ClassFilter {

    boolean match(Class<?> clazz);
}
