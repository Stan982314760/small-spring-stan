package com.stan.spring.beans.factory;

import com.stan.spring.beans.BeansException;
import com.stan.spring.beans.bean.InitializingBean;

/**
 * @Author: stan
 * @Date: 2021/09/30
 * @Description:
 */
public class UserDaoFactoryBean implements FactoryBean<UserDao>, InitializingBean {

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<UserDao> getObjectType() {
        return UserDao.class;
    }

    @Override
    public UserDao getObject() throws BeansException {
        return new UserDao();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("userDaoFactoryBean afterPropertiesSet...");
    }

    public void init() {
        System.out.println("userDaoFactoryBean init-method...");
    }


    public void destroy() {
        System.out.println("userDaoFactoryBean destroy-method...");
    }
}
