package com.stan.spring.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: DefaultResourceLoader
 */
public class DefaultResourceLoader implements ResourceLoader {

    private static final String CLASS_PATH_PREFIX = "classpath:";


    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASS_PATH_PREFIX)) {
            return new ClasspathResource(location.substring(CLASS_PATH_PREFIX.length()));
        } else  {
            try {
                return new UrlResource(new URL(location));
            } catch (MalformedURLException ignore) {
                return new FileSystemResource(location);
            }
        }
    }
}
