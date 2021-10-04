package com.stan.spring.context.support;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.factory.ConfigurableListableBeanFactory;
import com.stan.spring.beans.factory.config.ApplicationContextAwareProcessor;
import com.stan.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.stan.spring.beans.factory.config.BeanPostProcessor;
import com.stan.spring.context.ApplicationEvent;
import com.stan.spring.context.ApplicationListener;
import com.stan.spring.context.ConfigurableApplicationContext;
import com.stan.spring.context.event.ApplicationEventMulticaster;
import com.stan.spring.context.event.ContextClosedEvent;
import com.stan.spring.context.event.ContextRefreshedEvent;
import com.stan.spring.context.event.SimpleApplicationEventMulticaster;
import com.stan.spring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: AbstractApplicationContext
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private static final String APPLICATION_EVENT_MULTICASTER = "applicationEventMulticaster";

    private ApplicationEventMulticaster eventMulticaster;

    @Override
    public void refresh() {
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        registerApplicationContextAwareBeanPostProcessor(beanFactory);

        invokeBeanFactoryPostProcessors(beanFactory);

        registerBeanPostProcessors(beanFactory);

        beanFactory.preInstantiateSingletons();

        initApplicationEventMulticaster();

        registerListeners();

        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    private void registerListeners() {
        Collection<ApplicationListener> listeners = getBeanFactory().getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : listeners) {
            eventMulticaster.addApplicationListener(listener);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        eventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER, eventMulticaster);
    }


    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }


    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> postProcessorMap = getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor postProcessor : postProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(postProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> postProcessorMap = getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor factoryPostProcessor : postProcessorMap.values()) {
            factoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }


    private void registerApplicationContextAwareBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName, Object[] args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }


    @Override
    public <T> T getBean(String beanName, Class<T> type) throws BeansException {
        return getBeanFactory().getBean(beanName, type);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        eventMulticaster.multicastEvent(event);
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();
}
