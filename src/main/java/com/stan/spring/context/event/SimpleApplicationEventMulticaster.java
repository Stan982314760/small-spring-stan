package com.stan.spring.context.event;

import com.stan.spring.context.ApplicationEvent;
import com.stan.spring.context.ApplicationListener;

import java.util.Collection;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: SimpleApplicationEventMulticaster
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(ApplicationEvent event) {
        Collection<ApplicationListener> supportedListeners = getSupportedListeners(event);
        for (ApplicationListener listener : supportedListeners) {
            listener.onApplicationEvent(event);
        }
    }
}
