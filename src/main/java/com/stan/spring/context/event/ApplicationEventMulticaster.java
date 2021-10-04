package com.stan.spring.context.event;

import com.stan.spring.context.ApplicationEvent;
import com.stan.spring.context.ApplicationListener;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ApplicationEventMulticaster
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<? extends ApplicationEvent> listener);

    void removeApplicationListener(ApplicationListener<? extends ApplicationEvent> listener);

    void multicastEvent(ApplicationEvent event);
}
