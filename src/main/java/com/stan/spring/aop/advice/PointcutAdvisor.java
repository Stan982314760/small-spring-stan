package com.stan.spring.aop.advice;

import com.stan.spring.aop.Pointcut;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: PointcutAdvisor
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
