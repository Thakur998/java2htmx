package com.j2htmx.auto.base;

import java.io.IOException;
import java.util.Properties;

public interface CSSStorageStrategy {

    Properties load(String source) throws IOException;

    void save(Properties props, String destination) throws IOException;
}