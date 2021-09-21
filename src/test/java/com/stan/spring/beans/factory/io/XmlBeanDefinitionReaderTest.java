package com.stan.spring.beans.factory.io;

import com.stan.spring.beans.factory.UserService;
import com.stan.spring.beans.factory.support.DefaultListableBeanFactory;
import com.stan.spring.beans.factory.support.xml.XmlBeanDefinitionReader;
import com.stan.spring.core.io.DefaultResourceLoader;
import org.junit.Test;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: XmlBeanDefinitionReaderTest
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void testLoadBeanDefinitions() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, new DefaultResourceLoader());
        reader.loadBeanDefinition("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUser();
    }
}
