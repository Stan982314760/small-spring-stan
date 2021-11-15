package com.stan.spring.aop.aspectj;

import com.stan.spring.aop.Pointcut;
import com.stan.spring.aop.advice.Advice;
import com.stan.spring.aop.advice.PointcutAdvisor;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: AspectJExpressionPointcutAdvisor
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private String expression;

    private Advice advice;


    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
