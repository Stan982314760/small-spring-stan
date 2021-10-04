package com.stan.spring.context.event;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ContextClosedEvent
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
