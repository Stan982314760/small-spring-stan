package com.stan.spring.beans.factory.support;

import com.stan.spring.core.io.Resource;
import com.stan.spring.core.io.ResourceLoader;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: BeanDefinitionReader
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(String location);

    void loadBeanDefinition(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String... locations);

}
