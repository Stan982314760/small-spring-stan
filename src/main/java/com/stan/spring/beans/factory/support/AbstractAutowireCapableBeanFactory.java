package com.stan.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.PropertyValue;
import com.stan.spring.beans.PropertyValues;
import com.stan.spring.beans.bean.DisposableBean;
import com.stan.spring.beans.bean.DisposableBeanAdapter;
import com.stan.spring.beans.bean.InitializingBean;
import com.stan.spring.beans.factory.BeanClassLoaderAware;
import com.stan.spring.beans.factory.BeanFactoryAware;
import com.stan.spring.beans.factory.BeanNameAware;
import com.stan.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanPostProcessor;
import com.stan.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static cn.hutool.core.util.ClassLoaderUtil.getClassLoader;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: AbstractAutowireCapableBeanFactory
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition bd, Object[] args) {
        Object bean;
        try {
            bean = createBeanInstance(beanName, bd, args);
            applyPropertyValues(bean, bd);
            bean = initializeBean(bean, beanName, bd);
        } catch (Exception e) {
            throw new BeansException("Create {" + beanName + "} error", e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, bd);

        if (bd.isSingleton()) {
            registerSingleton(beanName, bean);
        }

        return bean;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition bd) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(bd.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, bd));
        }
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

        invokeAwareMethods(bean, beanName);

        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        try {
            invokeInitMethods(beanName, wrappedBean, bd);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        wrappedBean = applyBeanPostProcessorAfterInitialization(wrappedBean, beanName);

        return wrappedBean;
    }

    private void invokeAwareMethods(Object bean, String beanName) {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        if (bean instanceof BeanNameAware) {
            ((BeanNameAware) bean).setBeanName(beanName);
        }
        if (bean instanceof BeanClassLoaderAware) {
            ((BeanClassLoaderAware) bean).setBeanClassLoader(getClassLoader());
        }
    }


    private void invokeInitMethods(String beanName, Object bean, BeanDefinition bd) throws Exception {
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        String initMethodName = bd.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName) &&
                !(bean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))) {
            Method method = bean.getClass().getMethod(initMethodName);
            method.invoke(bean);
        }
    }


    private InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }


    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
