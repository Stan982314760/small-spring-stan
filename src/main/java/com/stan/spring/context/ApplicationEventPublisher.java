package com.stan.spring.context;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ApplicationEventPublisher
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
