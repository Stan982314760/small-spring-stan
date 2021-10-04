package com.stan.spring.beans.factory;

import com.stan.spring.context.ApplicationEvent;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: MyEvent
 */
public class MyEvent extends ApplicationEvent  {

    private String eventDesc = "test event and listener";


    public MyEvent(Object source) {
        super(source);
    }

    public String getEventDesc() {
        return eventDesc;
    }

}
