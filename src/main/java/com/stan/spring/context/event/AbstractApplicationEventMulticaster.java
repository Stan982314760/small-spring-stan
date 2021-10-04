package com.stan.spring.context.event;

import com.stan.spring.beans.BeansException;
import com.stan.spring.context.ApplicationEvent;
import com.stan.spring.context.ApplicationListener;
import com.stan.spring.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: AbstractApplicationEventMulticaster
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {

    private final Set<ApplicationListener<? extends ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    @Override
    public void addApplicationListener(ApplicationListener<? extends ApplicationEvent> listener) {
        applicationListeners.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<? extends ApplicationEvent> listener) {
        applicationListeners.remove(listener);
    }

    protected Collection<ApplicationListener> getSupportedListeners(ApplicationEvent event) {
        List<ApplicationListener> supportedListeners = new LinkedList<>();
        for (ApplicationListener<? extends ApplicationEvent> listener : applicationListeners) {
            if (supportEvent(listener, event)) {
                supportedListeners.add(listener);
            }
        }
        return supportedListeners;
    }

    private boolean supportEvent(ApplicationListener<? extends ApplicationEvent> listener, ApplicationEvent event) {
        Class<?> listenerClass = listener.getClass();
        if (ClassUtils.isCglibObject(listenerClass)) {
            listenerClass = listenerClass.getSuperclass();
        }

        ParameterizedType genericInterface = (ParameterizedType) listenerClass.getGenericInterfaces()[0];
        Type actualTypeArgument = genericInterface.getActualTypeArguments()[0];
        Class<?> eventClass;
        try {
            eventClass = Class.forName(actualTypeArgument.getTypeName());
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event type: " + actualTypeArgument.getTypeName());
        }

        return eventClass.isAssignableFrom(event.getClass());
    }

}
