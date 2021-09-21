package com.stan.spring.beans;

/**
 * @Author: stan
 * @Date: 2021/09/19
 * @Description: BeansException
 */
public class BeansException extends RuntimeException {

    public BeansException() {
        super();
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeansException(Throwable cause) {
        super(cause);
    }
}
