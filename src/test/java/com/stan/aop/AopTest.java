package com.stan.aop;

import com.stan.spring.aop.AdvisedSupport;
import com.stan.spring.aop.TargetSource;
import com.stan.spring.aop.aspectj.AspectJExpressionPointcut;
import com.stan.spring.aop.framework.AopProxy;
import com.stan.spring.aop.framework.JdkDynamicAopProxy;
import com.stan.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: AopTest
 */
public class AopTest {


    @Test
    public void testProxyInvocation() {
        String expression = "execution(* com.stan.aop.OrderService.order(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(expression);

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(new OrderServiceImpl()));
        advisedSupport.setMethodInterceptor(new OrderServiceInterceptor());
        advisedSupport.setMethodMatcher(pointcut);

        AopProxy proxy = new JdkDynamicAopProxy(advisedSupport);
        OrderService orderService = (OrderService) proxy.getProxy();

        orderService.order("KFC");

    }


    @Test
    public void testAopProxy() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");
        OrderService orderService = ctx.getBean("orderService", OrderService.class);
        orderService.order("KFC");
    }
}
