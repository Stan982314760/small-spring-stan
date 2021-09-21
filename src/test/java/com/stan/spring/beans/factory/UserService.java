package com.stan.spring.beans.factory;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description:
 */
public class UserService {

    private String userId;

    private UserDao userDao;

    public UserService() {
        System.out.println("userService instantiation...");
    }


    public void queryUser() {
        System.out.println("result : " + userDao.queryUser(userId));
    }
}
