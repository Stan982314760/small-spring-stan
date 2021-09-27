package com.stan.spring.beans.factory.config;

import com.stan.spring.beans.factory.ApplicationContextAware;
import com.stan.spring.context.ApplicationContext;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: ApplicationContextAwareProcessor
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
