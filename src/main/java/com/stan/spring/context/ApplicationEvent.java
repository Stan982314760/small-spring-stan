package com.stan.spring.context;

import java.util.EventObject;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ApplicationEvent
 */
public class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
