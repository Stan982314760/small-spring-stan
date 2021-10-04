package com.stan.spring.util;

/**
 * @Author: stan
 * @Date: 2021/10/04
 * @Description: ClassUtils
 */
public abstract class ClassUtils {

    public static boolean isCglibObject(Class<?> clazz) {
        return (clazz != null && isCglibObject(clazz.getName()));
    }

    private static boolean isCglibObject(String clazzName) {
        return clazzName.contains("$$");
    }
}
