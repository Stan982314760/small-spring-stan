package com.stan.spring.beans.factory;

import com.stan.spring.beans.bean.DisposableBean;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description:
 */
public class UserService implements DisposableBean {

    private String userId;

    private UserDao userDao;

    public void queryUser() {
        System.out.println("result : " + userDao.queryUser(userId));
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("userService destroy...");
    }
}
