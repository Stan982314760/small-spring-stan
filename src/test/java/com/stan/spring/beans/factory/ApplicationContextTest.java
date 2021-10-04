package com.stan.spring.beans.factory;

import com.stan.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Author: stan
 * @Date: 2021/09/21
 * @Description: ApplicationContextTest
 */
public class ApplicationContextTest {

    @Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.queryUser();
    }


    @Test
    public void test2() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ctx.registerShutdownHook();
        UserService userService = ctx.getBean("userService", UserService.class);
        userService.queryUser();
    }
}
