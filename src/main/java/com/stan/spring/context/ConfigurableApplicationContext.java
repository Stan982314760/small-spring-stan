package com.stan.spring.context;

import com.stan.spring.beans.BeansException;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: ConfigurableApplicationContext
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;

}
