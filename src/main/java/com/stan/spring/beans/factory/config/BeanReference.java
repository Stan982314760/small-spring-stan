package com.stan.spring.beans.factory.config;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: BeanReference
 */
public class BeanReference {

    private String refName;

    public BeanReference(String refName) {
        this.refName = refName;
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
    }


}
