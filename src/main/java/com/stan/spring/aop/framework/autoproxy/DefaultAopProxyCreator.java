package com.stan.spring.aop.framework.autoproxy;

import cn.hutool.core.bean.BeanException;
import com.stan.spring.aop.*;
import com.stan.spring.aop.advice.Advice;
import com.stan.spring.aop.advice.Advisor;
import com.stan.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.BeanFactory;
import com.stan.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.stan.spring.beans.factory.support.DefaultListableBeanFactory;

import java.util.Collection;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: DefaultAopProxyCreator
 */
public class DefaultAopProxyCreator implements InstantiationAwareBeanPostProcessor {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> expressionPointcutCollection = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor pointcutAdvisor : expressionPointcutCollection) {
            if (pointcutAdvisor.getPointcut().getClassFilter().match(beanClass)) {
                AdvisedSupport advisedSupport = new AdvisedSupport();
                TargetSource targetSource = null;
                try {
                    targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    throw new BeansException("create bean " + beanName + " error: " + e.getMessage());
                }
                advisedSupport.setTargetSource(targetSource);
                advisedSupport.setMethodInterceptor(((MethodInterceptor) pointcutAdvisor.getAdvice()));
                advisedSupport.setMethodMatcher(pointcutAdvisor.getPointcut().getMethodMatcher());

                return new ProxyFactory(advisedSupport).getProxy();
            }
        }

        return null;
    }



    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
