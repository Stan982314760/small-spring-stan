package com.stan.spring.beans.factory;

import com.stan.spring.beans.PropertyValue;
import com.stan.spring.beans.PropertyValues;
import com.stan.spring.beans.factory.config.BeanDefinition;
import com.stan.spring.beans.factory.config.BeanReference;
import com.stan.spring.beans.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: BeanFactoryTest
 */
public class BeanFactoryTest {

    private DefaultListableBeanFactory beanFactory;
    private BeanDefinition userDaoBd;
    private BeanDefinition userServiceBd;
    private PropertyValues propertyValues;

    @Before
    public void beforeTest() {
        beanFactory = new DefaultListableBeanFactory();

        userDaoBd = new BeanDefinition(UserDao.class);

        propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "3"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        userServiceBd = new BeanDefinition(UserService.class, propertyValues);
    }


    @Test
    public void testGetBean() {
        beanFactory.registerBeanDefinition("userDao", userDaoBd);
        beanFactory.registerBeanDefinition("userService", userServiceBd);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUser();
    }

    @Test
    public void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create(new Class[]{String.class}, new Object[]{"stan"});

    }
}
