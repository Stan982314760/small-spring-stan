package com.stan.spring.core.io;

import cn.hutool.core.util.ClassUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: ClasspathResource
 */
public class ClasspathResource implements Resource {

    private final String name;
    private final ClassLoader classLoader;

    public ClasspathResource(String name) {
        this(name, null);
    }

    public ClasspathResource(String name, ClassLoader classLoader) {
        this.name = name;
        this.classLoader = classLoader == null ? ClassUtil.getClassLoader() : classLoader;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(name);
        if (inputStream == null)
            throw new FileNotFoundException(name + " can not be found!");

        return inputStream;
    }
}
