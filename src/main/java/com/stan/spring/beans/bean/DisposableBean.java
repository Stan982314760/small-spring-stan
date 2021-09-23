package com.stan.spring.beans.bean;

/**
 * @Author: stan
 * @Date: 2021/09/23
 * @Description: DisposableBean
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
