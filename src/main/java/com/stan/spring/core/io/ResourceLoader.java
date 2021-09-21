package com.stan.spring.core.io;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: ResourceLoader
 */
public interface ResourceLoader {

    Resource getResource(String location);
}
