package com.stan.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: Resource
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
