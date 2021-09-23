package com.stan.spring.beans.bean;

/**
 * @Author: stan
 * @Date: 2021/09/23
 * @Description: InitializingBean
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
