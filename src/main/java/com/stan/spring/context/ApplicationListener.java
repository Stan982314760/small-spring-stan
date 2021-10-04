package com.stan.spring.context;

import java.util.EventListener;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ApplicationListener
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
