package com.j2htmx.auto.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CSSLoader {

    private Properties props = new Properties();

    private CSSStorageStrategy storageStrategy;

    public CSSLoader() {

        try {
            load("css");
        } catch (Exception e) {
            System.out.println("Unable to load css");
        }
    }

    public CSSLoader(String source) {

        try {
            load(source);
        } catch (Exception e) {
            System.out.println("Unable to load css");
        }
    }

    public CSSLoader(CSSStorageStrategy strategy,
                     String source) {

        this.storageStrategy = strategy;

        try {
            load(source);
        } catch (Exception e) {
            System.out.println("Unable to load css");
        }
    }

    public void load() throws IOException {
        load("css");
    }

    public void load(String source)
            throws IOException {

        Properties merged = new Properties();

        Path cssPath =
                Path.of(source + ".css");

        Path propertiesPath =
                Path.of(source + ".properties");

        if (Files.exists(propertiesPath)) {

            CSSStorageStrategy propertiesStrategy =
                    new FileCSSStorageStrategy();

            merged.putAll(
                    propertiesStrategy.load(source)
            );
        }

        if (Files.exists(cssPath)) {

            CSSStorageStrategy cssStrategy =
                    new CssFileStorageStrategy();

            merged.putAll(
                    cssStrategy.load(source)
            );
        }

        this.props = merged;
    }

    public void saveProperty(String key,
                             String value,
                             String fileName) {

        if (props.containsKey(key)) {
            props.remove(key);
        }

        props.setProperty(key, value);

        try {

            if (storageStrategy == null) {
                storageStrategy =
                        new CssFileStorageStrategy();
            }

            storageStrategy.save(
                    props,
                    fileName
            );

        } catch (IOException e) {

            System.out.println(
                    "Failure in saving data"
            );
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public <K, V> Set<Map.Entry<Object, Object>>
    getAll() {

        return props.entrySet();
    }
}