package com.stan.spring.context.event;

import com.stan.spring.context.ApplicationContext;
import com.stan.spring.context.ApplicationEvent;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ApplicationContextEvent
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }


    public final ApplicationContext getApplicationContext() {
        return ((ApplicationContext) getSource());
    }
}
