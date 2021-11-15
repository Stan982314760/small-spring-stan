package com.stan.spring.aop.framework;

import com.stan.spring.aop.AdvisedSupport;
import com.stan.spring.aop.MethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: Cglib2AopProxy
 */
public class Cglib2AopProxy implements AopProxy {

    private final AdvisedSupport advisedSupport;

    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        return enhancer.create();
    }


    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advisedSupport;

        DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            MethodInvocation methodInvocation = new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args, proxy);
            if (advisedSupport.getMethodMatcher().match(method, advisedSupport.getTargetSource().getTarget().getClass())) {
                return advisedSupport.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }
    }


    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return methodProxy.invoke(getThis(), getArguments());
        }
    }
}
