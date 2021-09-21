package com.stan.spring.context.support;

import com.stan.spring.beans.factory.support.BeanDefinitionReader;
import com.stan.spring.beans.factory.support.DefaultListableBeanFactory;
import com.stan.spring.beans.factory.support.xml.XmlBeanDefinitionReader;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: AbstractXmlRefreshableApplicationContext
 */
public abstract class AbstractXmlRefreshableApplicationContext extends AbstractRefreshableApplicationContext {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] locations = getConfigLocations();
        if (locations != null && locations.length > 0) {
            beanDefinitionReader.loadBeanDefinitions(locations);
        }
    }

    protected abstract String[] getConfigLocations();
}
