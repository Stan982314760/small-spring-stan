package com.stan.spring.aop;

import com.stan.spring.aop.framework.AopProxy;
import com.stan.spring.aop.framework.Cglib2AopProxy;
import com.stan.spring.aop.framework.JdkDynamicAopProxy;

/**
 * @Author: stan
 * @Date: 2021/11/15
 * @Description: ProxyFactory
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;


    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }


    public Object getProxy() {
       return createAopProxy().getProxy();
    }


    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

        return new JdkDynamicAopProxy(advisedSupport);
    }

}
