package com.stan.spring.beans.factory;

import com.stan.spring.context.ApplicationListener;
import com.stan.spring.context.event.ApplicationContextEvent;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: MyListener
 */
public class MyListener implements ApplicationListener<ApplicationContextEvent> {

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        System.out.println("ApplicationContextEvent happen...");
    }
}
