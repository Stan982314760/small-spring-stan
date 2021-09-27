package com.stan.spring.beans.factory;

import com.stan.spring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Author: stan
 * @Date: 2021/09/27
 * @Description: AwareMethodTest
 */
public class AwareMethodTest {


    @Test
    public void test() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ctx.registerShutdownHook();
        UserService userService = ctx.getBean("userService", UserService.class);
        userService.queryUser();
    }
}
