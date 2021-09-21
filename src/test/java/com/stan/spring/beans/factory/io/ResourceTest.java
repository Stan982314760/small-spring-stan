package com.stan.spring.beans.factory.io;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.LineHandler;
import com.stan.spring.core.io.ClasspathResource;
import com.stan.spring.core.io.FileSystemResource;
import com.stan.spring.core.io.Resource;
import com.stan.spring.core.io.UrlResource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: ResourceTest
 */
public class ResourceTest {

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClasspathResource("test.properties");
        InputStream inputStream = resource.getInputStream();
        LineHandler handler = System.out::println;
        IoUtil.readUtf8Lines(inputStream, handler);
    }


    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("src/main/resource/test.properties");
        InputStream inputStream = resource.getInputStream();
        LineHandler handler = System.out::println;
        IoUtil.readUtf8Lines(inputStream, handler);
    }

    @Test
    public void testUrlResource() throws IOException {
        URL url = new URL("https://github.com/Stan982314760/gmall2020/blob/master/README.md");
        Resource resource = new UrlResource(url);
        InputStream inputStream = resource.getInputStream();
        LineHandler handler = System.out::println;
        IoUtil.readUtf8Lines(inputStream, handler);
    }
}
