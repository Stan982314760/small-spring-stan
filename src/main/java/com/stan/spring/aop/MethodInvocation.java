package com.stan.spring.aop;

import org.aopalliance.intercept.Invocation;

import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: MethodInvocation
 */
public interface MethodInvocation extends Invocation {


    Method getMethod();


}
