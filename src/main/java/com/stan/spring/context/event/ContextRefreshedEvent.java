package com.stan.spring.context.event;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ContextRefreshedEvent
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
