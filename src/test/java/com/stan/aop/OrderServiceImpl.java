package com.stan.aop;

/**
 * @Author: stan
 * @Date: 2021/10/15
 * @Description: OrderServiceImpl
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void order(String dishes) {
        System.out.println("start to order: " + dishes);
    }
}
