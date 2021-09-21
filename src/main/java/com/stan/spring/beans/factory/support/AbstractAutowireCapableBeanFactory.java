package com.stan.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.PropertyValue;
import com.stan.spring.beans.PropertyValues;
import com.stan.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanPostProcessor;
import com.stan.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: AbstractAutowireCapableBeanFactory
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition bd, Object[] args) {
        Object bean;
        try {
            bean = createBeanInstance(beanName, bd, args);
            applyPropertyValues(bean, bd);
            bean = initializeBean(bean, beanName, bd);
        } catch (Exception e) {
            throw new BeansException("Instantiate {" + beanName + "} error", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }


    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            if (current == null) {
                return null;
            }
            result = current;
        }

        return result;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            if (current == null) {
                return null;
            }
            result = current;
        }

        return result;
    }

    private Object createBeanInstance(String beanName, BeanDefinition bd, Object[] args) {
        Class<?> clazz = bd.getBeanClass();
        Constructor<?> ctorToUse = null;

        if (args != null) {
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                if (constructor.getParameterTypes().length == args.length) {
                    ctorToUse = constructor;
                    break;
                }
            }
        }

        return getInstantiationStrategy().instantiate(beanName, bd, ctorToUse, args);
    }

    private void applyPropertyValues(Object bean, BeanDefinition bd) {
        try {
            PropertyValues propertyValues = bd.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                Object pv = propertyValue.getValue();
                String pn = propertyValue.getName();
                if (pv instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) pv;
                    pv = getBean(beanReference.getRefName());
                }

                BeanUtil.setFieldValue(bean, pn, pv);
            }
        } catch (Exception e) {
            throw new BeansException("Set filed value error", e);
        }
    }


    private Object initializeBean(Object bean, String beanName, BeanDefinition bd) {
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        invokeInitMethods(bean, beanName, bd);

        wrappedBean = applyBeanPostProcessorAfterInitialization(bean, beanName);

        return wrappedBean;
    }


    private void invokeInitMethods(Object bean, String beanName, BeanDefinition bd) {

    }


    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }


    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
