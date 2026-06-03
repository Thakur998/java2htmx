package com.j2htmx.auto.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileCSSStorageStrategy implements CSSStorageStrategy {

    @Override
    public Properties load(String source) throws IOException {

        Properties props = new Properties();

        try (FileInputStream fis =
                     new FileInputStream(source + ".properties")) {

            props.load(fis);
        }

        return props;
    }

    @Override
    public void save(Properties props,
                     String destination) throws IOException {

        try (FileOutputStream fos =
                     new FileOutputStream(destination + ".properties")) {

            props.store(fos, null);
        }
    }
}