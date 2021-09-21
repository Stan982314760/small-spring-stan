package com.stan.spring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @Author: stan
 * @Date: 2021/09/20
 * @Description: FileSystemResource
 */
public class FileSystemResource implements Resource {

    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }


    public FileSystemResource(String path) {
        this.path = path;
        this.file = Paths.get(path).toFile();
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

}
